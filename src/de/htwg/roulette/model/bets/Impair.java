package de.htwg.roulette.model.bets;

public class Impair extends AbstractBet {

	public Impair(int money) {
		super(money);
	}

	@Override
	boolean checkBet(int number) {
		return (number%2 != 0);
	}

	@Override
	int getPossibleFields() {
		return 18; //number of odd fields
	}

}
