package de.htwg.roulette.model.bets;

public class Street extends AbstractBet {
	private int rowNum;

	public Street(int money, int numInRow) {
		super(money);
		rowNum = numInRow;
	}

	@Override
	boolean checkBet(int number) {
		if (!numInRange(number))
			return false;
		if (number == 0)
			return false;
		
		//Trick: 1-3 is now 0-2. Then we can make a division to compare it.
		int inputFactor = Math.floorDiv((number - 1), 3);
		int shouldFactor = Math.floorDiv((rowNum - 1), 3);
		
		return (inputFactor == shouldFactor);
	}

	@Override
	int getPossibleFields() {
		return 3;
	}

}
