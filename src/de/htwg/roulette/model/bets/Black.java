package de.htwg.roulette.model.bets;

import java.util.List;
import java.util.Arrays;

public class Black extends AbstractBet{
private static List<Integer> blacknumbers = Arrays.asList
		(2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 35);

	public Black(int money) {
		super(money);
		
	}

	@Override
	public boolean checkBet(int number) {
		return blacknumbers.contains(number);
	}

	@Override
	int getPossibleFields() {
		return blacknumbers.size();
	}

	@Override
	public
	String getName() {
		return "black Numbers";
	}
	
	

}