package de.htwg.roulette.model;

import java.util.*;
import de.htwg.roulette.model.bets.*;

public class User extends Account {	
	private List<AbstractBet> currentBets;
	

	public User(String newName, int newBalance) {
		super(newName, newBalance);
		currentBets = new LinkedList<>(); 
	}	
	
	public List<AbstractBet> getBets() {
		return new LinkedList<>(currentBets);
	}
	
	public void addBet(AbstractBet bet) {
		currentBets.add(bet);
	}
	
	public void clearBets(){
		currentBets.clear();
	}
	
}
