package de.htwg.roulette.model.bets;

public class TwoRows extends AbstractBet {
	private int rowNum;

	public TwoRows(int money, int numInRow) {
		super(money);
		rowNum = numInRow;
	}

	@Override
	boolean checkBet(int number) {
		if (!numInRange(number))
			return false;
		if (number == 0)
			return false;
		
		//Trick: 1-6 is now 0-5. Then we can make a division to compare it.
		int inputFactor = Math.floorDiv(number - 1, 6);
		int shouldFactor = Math.floorDiv(rowNum - 1, 6);
		
		return inputFactor == shouldFactor;
	}

	@Override
	int getPossibleFields() {
		return 6;
	}

	@Override
	public String getName() {
		return "Two rows";
	}

}
