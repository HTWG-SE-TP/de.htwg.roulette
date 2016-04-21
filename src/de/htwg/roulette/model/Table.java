package de.htwg.roulette.model;

import java.util.*;

import de.htwg.roulette.model.bets.AbstractBet;

public class Table {
	public final static int FIELD_SIZE = 37;
	
	private Account bank;
	private List<User> users;
	private int minimum, maximum;
	
	
	public Table(int min, int max) {
		minimum = min;
		maximum = max;
		
		bank = new Account("Bank", 0);
		users = new LinkedList<>();
	}
	
	public User createUser(String name, int balance){
		User newUser = new User(name, balance);
		users.add(newUser);
		return newUser;		
	}
	
	public void nextRound(){
		//Rand
		//Gehen durch alle Spieler
		//Wetten pr�fen, Konto korrigieren 
		int number = (int)Math.round(Math.random() * FIELD_SIZE);
		
		System.out.printf("Picked Number %d \n", number);
		
		for (User u : users){
			int ball = u.getBalance();
			for (AbstractBet bet : u.getBets()) {
				ball += bet.betResult(number);
			}
			u.setBalance(ball);
		}

	}
	
	

}
