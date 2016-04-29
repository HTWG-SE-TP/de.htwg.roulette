package de.htwg.roulette.controller;
import java.util.*;

import de.htwg.roulette.model.*;
import de.htwg.roulette.model.bets.*;

public class Controller {

	private Table Table;
	private Account bank;
	private List<User> players;
	
	public Controller(){
		bank = new Account("Bank", 0);
		Table = new Table(10, 1000);
		players = new LinkedList<User>();
	}
	
	public boolean addPlayer(String name, int balance) {
		for(User p : players){
			if (p.getName().equals(name))
				return false;
		}
		
		User newUser = new User(name, balance);
		players.add(newUser);
		return true;
	}
	
	public void removePlayer(String name) {
		for(User p : players){
			if (p.getName().equals(name)){
				players.remove(p);
				return;
			}
		}
	}
	
	public List<User> getBets() {
		//Deep copy
		return new LinkedList<>(players);
	}
	
	public void nextRound(){
		//Rand
		//Gehen durch alle Spieler
		//Wetten prüfen, Konto korrigieren 
		int number = (int)Math.round(Math.random() * Table.FIELD_SIZE);
		
		System.out.printf("Picked Number %d \n", number);
	
		for (User u : players){
			int ball = u.getBalance();
			for (AbstractBet bet : u.getBets()) {
				int result = bet.betResult(number);
				
				ball += result;
				bank.setBalance(bank.getBalance()- result); //update bank's balance
			}
			u.setBalance(ball);
		}

	}
}
