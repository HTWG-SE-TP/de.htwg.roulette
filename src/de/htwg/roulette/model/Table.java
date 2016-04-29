package de.htwg.roulette.model;

import java.util.*;

import de.htwg.roulette.model.bets.AbstractBet;

public class Table {
	public final static int FIELD_SIZE = 37;
	private int minimum, maximum;
	
	
	public Table(int min, int max) {
		minimum = min;
		maximum = max;
	}
	
	public boolean checkBet(AbstractBet bet){
		return bet.getStake() >= minimum || bet.getStake() <= maximum;
	}
}
