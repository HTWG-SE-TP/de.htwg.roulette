package de.htwg.roulette.controller;

import java.util.List;

import com.google.inject.Injector;

import de.htwg.roulette.model.Account;
import de.htwg.roulette.model.IUser;
import de.htwg.roulette.model.bets.IBet;
import de.htwg.util.Visitor.Visitor;
import de.htwg.util.observer.Observable;

public interface IController extends de.htwg.util.Visitor.Visitable {
	void create(Observable observ, Injector inj);
	
	void nextRound();
	void addPlayer(String name, int balance);
	void removePlayer(String name);
	void placeBet(String name, IBet bet);

	List<IUser> getPlayers();
	Account getBank();
	int getRound();
	int getBetCount();
	
	void calculateResult(Visitor player, Observable observer, int number);

}