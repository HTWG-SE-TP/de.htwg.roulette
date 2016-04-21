package de.htwg.roulette.model.bets;

public class Pair extends AbstractBet {

	public Pair(int money) {
		super(money);
	}

	@Override
	boolean checkBet(int number) {
		return (number%2 == 0);
	}

	@Override
	int getPossibleFields() {
		return 18; //number of even fields
	}

}
