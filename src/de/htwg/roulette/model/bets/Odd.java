package de.htwg.roulette.model.bets;

public class Odd extends AbstractBet {

	public Odd(int money) {
		super(money);
	}

	@Override
	boolean checkBet(int number) {
		if (!numInRange(number)) 
			return false;
		return (number%2 == 1);
	}

	@Override
	int getPossibleFields() {
		return 18; //number of odd fields
	}

}
