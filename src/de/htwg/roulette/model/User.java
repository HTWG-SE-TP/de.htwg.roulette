package de.htwg.roulette.model;

import java.util.*;
import de.htwg.roulette.model.bets.*;

public class User extends Account {
	public User(String newName, int newBalance) {
		super(newName, newBalance);
		
	}
	
	private List<AbstractBet> currentBets;
	
	public List<AbstractBet> getBets() {
		return new LinkedList<>(currentBets);
	}
	
	public void addBet(AbstractBet bet) {
		currentBets.add(bet);
	}
	
}
