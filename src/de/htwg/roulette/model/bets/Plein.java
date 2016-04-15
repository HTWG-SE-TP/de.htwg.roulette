package de.htwg.roulette.model.bets;

public class Plein extends AbstractBet {
	int number = 0;
	
	public Plein(int money, int num) {
		super(money);
		number = num;
	}

	@Override
	boolean checkBet(int number) {
		return this.number == number;
	}

	@Override
	int getPossibleFields() {
		return 18; //number of even fields
	}

}
