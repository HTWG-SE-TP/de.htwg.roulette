package de.htwg.roulette.model.bets;

public class Manque extends AbstractBet {

	public Manque(int money) {
		super(money);
	}

	@Override
	int getPossibleFields() {
		return 18;
	}

	@Override
	boolean checkBet(int number) {
		return (number >= 1 && number <= 18);
	}

}
