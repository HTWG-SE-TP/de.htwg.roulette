package de.htwg.roulette.model;

import java.util.LinkedList;
import java.util.List;

import de.htwg.roulette.model.bets.IBet;
import de.htwg.roulette.model.events.BetResultEvent;
import de.htwg.util.Visitor.Visitor;
import de.htwg.util.observer.Observable;


public class User extends Account implements Visitor {	
	private List<IBet> currentBets;
	private Account bank;
	

	public User(Account bank, String newName, int newBalance) {
		super(newName, newBalance);
		currentBets = new LinkedList<>();
		this.bank = bank;
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
	public void visit(Observable observer, int number) {
		int ball = this.getBalance();
		for (IBet bet : this.getBets()) {
			int result = bet.betResult(number);
			BetResultEvent event = new BetResultEvent(this, bet, result);				
			observer.notifyObservers(event);
			
			updateBank(bank, result);
			ball += result;
		}
		this.clearBets();
		this.setBalance(ball);

	}

	public void updateBank(Account bank, int result) {
		bank.setBalance(bank.getBalance() - result); // update bank's balance
		
	}
}
