package de.htwg.roulette.model.bets;

public class SingleNumber extends AbstractBet {
	int number = 0;
	
	public SingleNumber(int money, int num) {
		super(money);
		number = num;
	}

	@Override
	boolean checkBet(int number) {
		return this.number == number;
	}

	@Override
	int getPossibleFields() {
		return 1;
	}

	@Override
	public
	String getName() {
		return String.format("a single Number (%d)", this.number);
	}
}
