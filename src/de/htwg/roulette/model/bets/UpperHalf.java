package de.htwg.roulette.model.bets;

public class UpperHalf extends AbstractBet {

	public UpperHalf(int money) {
		super(money);
	}

	@Override
	int getPossibleFields() {
		return 18;
	}

	@Override
	boolean checkBet(int number) {
		return (number >= 19 && number <= 36);
	}

}
