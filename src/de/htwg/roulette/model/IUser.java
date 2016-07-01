package de.htwg.roulette.model;

import java.util.List;

import de.htwg.roulette.model.bets.IBet;
import de.htwg.util.Visitor.Visitor;
import de.htwg.util.observer.Observable;

public interface IUser extends Visitor{
	/** Pseudo constructor.
	 * @param bank The main bank account
	 * @param name Playername
	 * @param balance
	 */
	void create(Account bank, String name, int balance);
	
	/** Returns a list of the bets of the user.
	 * @return the list
	 */
	List<IBet> getBets();
	
	
	/** Adds a bet for this user.
	 * @param bet The Bet
	 */
	void addBet(IBet bet);
	
	
	/**
	 *  Removes all bets of this user.
	 */
	void clearBets();
	
	@Override
	void visit(Observable observer, int number);
	
	/** Updates the bank balance
	 * @param bank Bank object
	 * @param result Money added/removed
	 */
	void updateBank(Account bank, int result);

	/** Return the username.
	 * @return Name
	 */
	public String getName();
	
	/** Sets the username.
	 * @param name
	 */
	public void setName(String name);

	/** Returns the balance
	 * @return balance
	 */
	public int getBalance();
	
	
	/** Updates the balance
	 * @param balance Money
	 */
	public void setBalance(int balance);
}