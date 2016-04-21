package de.htwg.roulette.model.bets;

public class Cheval extends AbstractBet {
	int num[] = new int[2];
	
	public Cheval(int money, int num1, int num2) {
		super(money);
		num[0] = num1;
		num[1] = num2;
	}

	@Override
	boolean checkBet(int number) {
		return num[0] == number || num[1] == number;
	}

	@Override
	int getPossibleFields() {
		return 18; //number of even fields
	}

}
