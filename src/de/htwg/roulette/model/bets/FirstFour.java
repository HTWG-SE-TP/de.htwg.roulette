package de.htwg.roulette.model.bets;

public class FirstFour extends AbstractBet {

	public FirstFour(int money) {
		super(money);
	}

	@Override
	int getPossibleFields() {
		return 4;
	}

	@Override
	boolean checkBet(int number) {
		return (number>0 && number < 5);
	}

}
