package de.htwg.roulette.model.bets;

public class Even extends AbstractBet {

	public Even(int money) {
		super(money);
	}

	@Override
	boolean checkBet(int number) {	
		if (!numInRange(number)) 
			return false;
		return (number%2 == 0);
	}

	@Override
	int getPossibleFields() {
		return 18; //number of even fields
	}

}
