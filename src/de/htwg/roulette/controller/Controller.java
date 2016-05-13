package de.htwg.roulette.controller;



import java.util.LinkedList;
import java.util.List;

import de.htwg.roulette.model.*;
import de.htwg.roulette.model.bets.*;
import de.htwg.util.observer.Observable;

public class Controller  {

	private Table table;
	private Account bank;
	private List<User> players;
	private int roundCount = 1;
	private Observable observer;
	
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
			int ball = u.getBalance();
			for (AbstractBet bet : u.getBets()) {
				int result = bet.betResult(number);
				BetResultEvent event = new BetResultEvent(u, bet, result);				
				observer.notifyObservers(event);
				
				ball += result;
				bank.setBalance(bank.getBalance() - result); // update bank's
																// balance
			}
			u.clearBets();
			u.setBalance(ball);
		}

		roundCount++;
	}

	public boolean addPlayer(String name, int balance) {
		for (User p : players) {
			if (p.getName().equals(name))
				return false;
		}

		User newUser = new User(name, balance);
		players.add(newUser);
		return true;
	}

	public boolean removePlayer(String name) {
		for (User p : players) {
			if (p.getName().equals(name)) {
				players.remove(p);
				return true;
			}
		}
		return false;
	}

	public boolean placeBet(String name, AbstractBet bet) {
		if (name == null || bet == null)
			return false;
		
		for (User p : players) {
			if (p.getName().equals(name)){
				if (!checkBetConditions(bet, p))
					return false;
				p.addBet(bet);
				return true;
			}		
		}
		return false;
	}

	private boolean checkBetConditions(AbstractBet bet, User p) {
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
}
