package de.htwg.roulette.controller;

import java.util.*;

import de.htwg.roulette.model.*;
import de.htwg.roulette.model.bets.*;

public class Controller {

	private Table table;
	private Account bank;
	private List<User> players;
	private int roundCount = 1;

	public Controller() {
		bank = new Account("Bank", 0);
		table = new Table(10, 1000);
		players = new LinkedList<User>();
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

	public void removePlayer(String name) {
		for (User p : players) {
			if (p.getName().equals(name)) {
				players.remove(p);
				return;
			}
		}
	}

	public List<User> getPlayers() {
		// Deep copy
		return new LinkedList<>(players);
	}

	public int getRound(){
		return roundCount;
	}
}
