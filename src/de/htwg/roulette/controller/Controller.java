package de.htwg.roulette.controller;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Injector;
import com.google.inject.Singleton;

import de.htwg.roulette.model.Account;
import de.htwg.roulette.model.IUser;
import de.htwg.roulette.model.Table;
import de.htwg.roulette.model.User;
import de.htwg.roulette.model.bets.IBet;
import de.htwg.roulette.model.events.BetAddedEvent;
import de.htwg.roulette.model.events.NextRoundEvent;
import de.htwg.roulette.model.events.PlayerEvent;
import de.htwg.util.Visitor.Visitor;
import de.htwg.util.observer.Observable;

@Singleton
public class Controller implements IController {
	private static final Logger LOGGER = LogManager.getLogger(Controller.class.getName());
	private Table table;
	private Account bank;
	private List<IUser> players;
	private int roundCount = 1;
	
	private Observable observer;
	private Injector injector;
	
	@Override
	public void create(Observable observ, Injector inj) {
		bank = new Account("Bank", 0);
		table = new Table(10, 1000);
		players = new LinkedList<IUser>();
		observer = observ;
		injector = inj;
	}

	@Override
	public void nextRound() {
		// Rand
		// Gehen durch alle Spieler
		// Wetten prüfen, Konto korrigieren
		int number = (int) Math.round(Math.random() * Table.FIELD_SIZE);

		for (IUser u : players) {
			try {
				calculateResult(u, observer, number); // Visitor Pattern
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}

		roundCount++;
		observer.notifyObservers(new NextRoundEvent(number));
	}

	@Override
	public void addPlayer(String name, int balance) {
		boolean alreadyAdded = false;
		for (IUser p : players) {
			if (p.getName().equals(name))
				alreadyAdded = true;
			break;
		}

		if (!alreadyAdded) {
			IUser newUser = injector.getInstance(IUser.class);
			newUser.create(bank, name, balance);
			players.add(newUser);
		}
		observer.notifyObservers(new PlayerEvent(name, true, !alreadyAdded));
	}

	@Override
	public void removePlayer(String name) {
		boolean result = false;
		for (IUser p : players) {
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
			for (IUser p : players) {
				if (p.getName().equals(name) && checkBetConditions(bet, p)) {
					p.addBet(bet);
					result = true;
					break;
				}
			}
		}

		observer.notifyObservers(new BetAddedEvent(name, bet, result));
	}

	private boolean checkBetConditions(IBet bet, IUser p) {
		if (bet.getStake() < 1)
			return false;
		if (p.getBalance() - bet.getStake() < 0)
			return false;
		if (!table.checkBet(bet))
			return false;
		return true;
	}

	@Override
	public List<IUser> getPlayers() {
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
		for (IUser p : players)
			sum += p.getBets().size();
		return sum;
	}

	@Override
	public void calculateResult(Visitor player, Observable observer, int number) {
		player.visit(observer, number);

	}

	@Override
	public Account getBank() {
		return bank;
	}


}
