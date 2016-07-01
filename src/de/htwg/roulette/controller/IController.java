package de.htwg.roulette.controller;

import java.util.List;

import com.google.inject.Injector;

import de.htwg.roulette.model.Account;
import de.htwg.roulette.model.IUser;
import de.htwg.roulette.model.bets.IBet;
import de.htwg.util.Visitor.Visitor;
import de.htwg.util.observer.Observable;

public interface IController extends de.htwg.util.Visitor.Visitable {
	/** Create method that replaces the constructor.
	 * @param observ The main observer used
	 * @param inj The guice injector used
	 */
	void create(Observable observ, Injector inj);


	/**
	 * Start next round.
	 */
	void nextRound();
	
	
	/** Adds a player to the game.
	 * @param name Playername
	 * @param balance $$$
	 */
	void addPlayer(String name, int balance);
	
	/** Removes a player from the game.
	 * @param name Playername
	 */
	void removePlayer(String name);
	
	
	/** Add a (vaild) bet to the game.
	 * @param name Playername
	 * @param bet The bet
	 */
	void placeBet(String name, IBet bet);

	/** Gets all players of the game.
	 * @return the list
	 */
	List<IUser> getPlayers();
	
	
	/** Return the bank account.
	 * @return the account
	 */
	Account getBank();
	
	
	/** Gets the round count.
	 * @return the count
	 */
	int getRound();
	
	
	/** Total bet count.
	 * @return bet count
	 */
	int getBetCount();
	
	@Override
	void calculateResult(Visitor player, Observable observer, int number);

}