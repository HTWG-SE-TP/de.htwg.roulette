package de.htwg.roulette.controller;



import java.util.LinkedList;
import java.util.List;

import de.htwg.roulette.model.*;
import de.htwg.roulette.model.bets.*;
import de.htwg.util.Visitor.Visitor;
import de.htwg.util.observer.Observable;

public class Controller implements de.htwg.util.Visitor.Visitable {

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
			try{
				calculateResult(u, bank, observer, number ); //Visitor Pattern
			}catch(Exception e){
				e.printStackTrace();
			}
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

	public boolean placeBet(String name, IBet bet) {
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

	@Override
	public void calculateResult(User player, Account bank, Observable observer, int number) {
		player.visit(bank, observer, number);
		
	}
}
