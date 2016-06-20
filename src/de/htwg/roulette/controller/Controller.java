package de.htwg.roulette.controller;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.roulette.model.Account;
import de.htwg.roulette.model.Table;
import de.htwg.roulette.model.User;
import de.htwg.roulette.model.bets.IBet;
import de.htwg.roulette.model.events.BetAddedEvent;
import de.htwg.roulette.model.events.NextRoundEvent;
import de.htwg.roulette.model.events.PlayerEvent;
import de.htwg.util.Visitor.Visitor;
import de.htwg.util.observer.Observable;

public class Controller implements IController {
	private static final Logger LOGGER = LogManager.getLogger(Controller.class.getName());
	private Table table;
	private Account bank;
	private List<User> players;
	private int roundCount = 1;
	private Observable observer;
	
	@Override
	public void create(Observable observ) {
		bank = new Account("Bank", 0);
		table = new Table(10, 1000);
		players = new LinkedList<User>();
		observer = observ;
	}

	@Override
	public void nextRound() {
		// Rand
		// Gehen durch alle Spieler
		// Wetten prüfen, Konto korrigieren
		int number = (int) Math.round(Math.random() * Table.FIELD_SIZE);
		observer.notifyObservers(new NextRoundEvent(number));

		for (User u : players) {
			try {
				calculateResult(u, observer, number); // Visitor Pattern
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}

		roundCount++;
	}

	@Override
	public void addPlayer(String name, int balance) {
		boolean alreadyAdded = false;
		for (User p : players) {
			if (p.getName().equals(name))
				alreadyAdded = true;
			break;
		}

		if (!alreadyAdded) {
			User newUser = new User(bank, name, balance);
			players.add(newUser);
		}
		observer.notifyObservers(new PlayerEvent(name, true, !alreadyAdded));
	}

	@Override
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

	@Override
	public void placeBet(String name, IBet bet) {
		boolean result = false;
		if (name == null || bet == null) {
			result = false;

		} else {
			for (User p : players) {
				if (p.getName().equals(name) && checkBetConditions(bet, p)) {
					p.addBet(bet);
					result = true;
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

	@Override
	public List<User> getPlayers() {
		// Deep copy
		return new LinkedList<>(players);
	}

	@Override
	public int getRound() {
		return roundCount;
	}
	@Override
	public int getBetCount() {
		int sum = 0; 
		for (User p : players)
			sum += p.getBets().size();
		return sum;
	}

	@Override
	public void calculateResult(Visitor player, Observable observer, int number) {
		player.visit(observer, number);

	}


}
