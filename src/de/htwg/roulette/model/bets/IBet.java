package de.htwg.roulette.model.bets;

public interface IBet {
	//Strategy Pattern
	
	/** Computes if the bet is won or lost.
	 * @param number Rolled number
	 * @return Money won or lost in this bet
	 */
	int betResult(int number);
	
	
	/** Returns the name of the bet.
	 * @return name
	 */
	String getName();
	
	
	/** Returns the stake of this bet.
	 * @return stake
	 */
	int getStake();
}
