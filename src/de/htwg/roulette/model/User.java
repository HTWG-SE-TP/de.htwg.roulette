package de.htwg.roulette.model;

import java.util.*;

import de.htwg.roulette.model.bets.*;
import de.htwg.roulette.model.events.BetResultEvent;
import de.htwg.util.Visitor.Visitor;
import de.htwg.util.observer.Observable;


public class User extends Account implements Visitor {	
	private List<IBet> currentBets;
	

	public User(String newName, int newBalance) {
		super(newName, newBalance);
		currentBets = new LinkedList<>(); 
	}	
	
	public List<IBet> getBets() {
		return new LinkedList<>(currentBets);
	}
	
	public void addBet(IBet bet) {
		currentBets.add(bet);
	}
	
	public void clearBets(){
		currentBets.clear();
	}
	@Override
	public void visit(Account bank, Observable observer, int number) {
		int ball = this.getBalance();
		for (IBet bet : this.getBets()) {
			int result = bet.betResult(number);
			BetResultEvent event = new BetResultEvent(this, bet, result);				
			observer.notifyObservers(event);
			
			visit(bank, result);
			ball += result;
		}
		this.clearBets();
		this.setBalance(ball);

	}

	@Override
	public void visit(Account bank, int result) {
		bank.setBalance(bank.getBalance() - result); // update bank's balance
		
	}
}
