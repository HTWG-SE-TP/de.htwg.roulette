package de.htwg.roulette.controller;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.roulette.model.*;
import de.htwg.roulette.model.bets.*;
import de.htwg.roulette.model.events.BetAddedEvent;
import de.htwg.roulette.model.events.NextRoundEvent;
import de.htwg.roulette.model.events.PlayerEvent;
import de.htwg.roulette.tui.TextUI;
import de.htwg.util.Visitor.Visitor;
import de.htwg.util.observer.Observable;

public class Controller implements de.htwg.util.Visitor.Visitable {
	private static final Logger LOGGER = LogManager.getLogger(Controller.class.getName());
	private Table table;
	private Account bank;
	private List<User> players;
	private int roundCount = 1;
	private Observable observer;
	private Visitor visitor;

	public Controller(Observable observ) {
		bank = new Account("Bank", 0);
		table = new Table(10, 1000);
		players = new LinkedList<User>();
		observer = observ;
	}

	public void nextRound() {
		// Rand
		// Gehen durch alle Spieler
		// Wetten prüfen, Konto korrigieren
		int number = (int) Math.round(Math.random() * Table.FIELD_SIZE);
		observer.notifyObservers(new NextRoundEvent(number));

		for (User u : players) {
			try {
				calculateResult(u, bank, observer, number); // Visitor Pattern
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}

		roundCount++;
	}

	public void addPlayer(String name, int balance) {
		boolean alreadyAdded = false;
		for (User p : players) {
			if (p.getName().equals(name))
				alreadyAdded = true;
			break;
		}

		if (!alreadyAdded) {
			User newUser = new User(name, balance);
			players.add(newUser);
		}
		observer.notifyObservers(new PlayerEvent(name, true, !alreadyAdded));
	}

	public void removePlayer(String name) {
		boolean result = false;
		for (User p : players) {
			if (p.getName().equals(name)) {
				players.remove(p);
				result = true;
				break;
			}
		}
		observer.notifyObservers(new PlayerEvent(name, false, result));
	}

	public void placeBet(String name, IBet bet) {
		boolean result = false;
		if (name == null || bet == null) {
			result = false;

		} else {
			for (User p : players) {
				if (p.getName().equals(name)) {
					if (!checkBetConditions(bet, p)) {
						result = false;
					} else {
						p.addBet(bet);
						result = true;
					}
					break;
				}
			}
		}

		observer.notifyObservers(new BetAddedEvent(name, bet, result));
	}

	private boolean checkBetConditions(IBet bet, User p) {
		if (bet.getStake() < 1)
			return false;
		if (p.getBalance() - bet.getStake() < 0)
			return false;
		if (!table.checkBet(bet))
			return false;
		return true;
	}

	public List<User> getPlayers() {
		// Deep copy
		return new LinkedList<>(players);
	}

	public int getRound() {
		return roundCount;
	}
	public int getBetCount() {
		int sum = 0; 
		for (User p : players)
			sum += p.getBets().size();
		return sum;
	}

	@Override
	public void calculateResult(User player, Account bank, Observable observer, int number) {
		player.visit(bank, observer, number);

	}
}
