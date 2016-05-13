package de.htwg.roulette.model.bets;

import java.util.List;
import java.util.Arrays;

public class Red extends AbstractBet {
	private static List<Integer> redNumbers = Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32,
			34, 36);

	public Red(int money) {
		super(money);

	}

	@Override
	boolean checkBet(int number) {
		return redNumbers.contains(number);
	}

	@Override
	int getPossibleFields() {
		return redNumbers.size();

	}
	
	@Override
	public
	String getName() {
		return "red Numbers";
	}

}
