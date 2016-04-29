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
	
	public void addPlayer(String name, int balance) {
		User newUser = new User(name, balance);
		players.add(newUser);
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
