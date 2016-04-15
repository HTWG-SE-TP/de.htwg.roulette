package de.htwg.roulette.model.bets;

import java.util.List;
import java.util.Arrays;

public class Rouge extends AbstractBet{
private static List<Integer> colonne = Arrays.asList
		(1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34);

	public Rouge(int money) {
		super(money);
		
	}

	@Override
	boolean checkBet(int number) {
		return (colonne.contains(number));
	}

	@Override
	int getPossibleFields() {
		return colonne.size();
		
	}

}
