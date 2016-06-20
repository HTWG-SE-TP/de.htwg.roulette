package de.htwg.roulette.model;

import java.util.LinkedList;
import java.util.List;

import de.htwg.roulette.model.bets.IBet;
import de.htwg.roulette.model.events.BetResultEvent;
import de.htwg.util.Visitor.Visitor;
import de.htwg.util.observer.Observable;


public class User extends Account implements IUser {	
	private List<IBet> currentBets;
	private Account bank;
	

	public User() {
		super("", 0);
		//Pseduo
	}	
	
	public void create(Account bank, String newName, int newBalance){
		setName(newName);
		setBalance(newBalance);
		
		currentBets = new LinkedList<>();
		this.bank = bank;
	}
	
	@Override
	public List<IBet> getBets() {
		return new LinkedList<>(currentBets);
	}
	
	@Override
	public void addBet(IBet bet) {
		currentBets.add(bet);
	}
	
	@Override
	public void clearBets(){
		currentBets.clear();
	}

	@Override
	public void visit(Observable observer, int number) {
		int ball = this.getBalance();
		for (IBet bet : this.getBets()) {
			int result = bet.betResult(number);
			BetResultEvent event = new BetResultEvent(this.getName(), bet, result);				
			observer.notifyObservers(event);
			
			updateBank(bank, result);
			ball += result;
		}
		this.clearBets();
		this.setBalance(ball);

	}

	@Override
	public void updateBank(Account bank, int result) {
		bank.setBalance(bank.getBalance() - result); // update bank's balance
		
	}
}
