package de.htwg.roulette.controller;

import java.util.*;

import de.htwg.roulette.model.*;
import de.htwg.roulette.model.bets.*;

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

	public int nextRound() {
		// Rand
		// Gehen durch alle Spieler
		// Wetten prüfen, Konto korrigieren
		int number = (int) Math.round(Math.random() * Table.FIELD_SIZE);

		for (User u : players) {
			int ball = u.getBalance();
			for (AbstractBet bet : u.getBets()) {
				int result = bet.betResult(number);
				String resultInfo = generateBetString(u, bet, result);				
				observer.notifyObservers(resultInfo);
				
				ball += result;
				bank.setBalance(bank.getBalance() - result); // update bank's
																// balance
			}
			u.clearBets();
			u.setBalance(ball);
		}

		roundCount++;

		return number;
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
		if (bet == null || bet.getStake() < 1)
			return false;
		if (p.getBalance() - bet.getStake() < 0)
			return false;
		if (!table.checkBet(bet))
			return false;
		return true;
	}
	
	private String generateBetString(User p, AbstractBet bet, int result){
		String tmp;
		if (result >= 0){
			tmp = "won";
		} else {
			tmp = "lost";
		}
		
		return String.format("%s %s %d$ with his bet on %s", p.getName(), tmp, result, bet.toString());
	}

	public List<User> getPlayers() {
		// Deep copy
		return new LinkedList<>(players);
	}

	public int getRound() {
		return roundCount;
	}
}
