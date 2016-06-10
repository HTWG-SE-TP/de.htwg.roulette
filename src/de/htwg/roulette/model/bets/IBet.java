package de.htwg.roulette.model.bets;

public interface IBet {
	//Strategy Pattern
	int betResult(int number);
	String getName();
	int getStake();
}
