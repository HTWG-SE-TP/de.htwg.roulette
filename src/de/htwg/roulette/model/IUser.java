package de.htwg.roulette.model;

import java.util.List;

import de.htwg.roulette.model.bets.IBet;
import de.htwg.util.Visitor.Visitor;
import de.htwg.util.observer.Observable;

public interface IUser extends Visitor{
	void create(Account bank, String name, int balance);
	
	List<IBet> getBets();
	void addBet(IBet bet);
	void clearBets();
	
	@Override
	void visit(Observable observer, int number);
	void updateBank(Account bank, int result);

	public String getName();
	public void setName(String name);

	public int getBalance();
	public void setBalance(int balance);
}