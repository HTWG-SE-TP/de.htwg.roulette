package de.htwg.roulette.model.bets;

import java.util.*;

public class Corner extends AbstractBet{
	private List<Integer> cornerNums;

	public Corner(int money, List<Integer> nums) {
		super(money);
				
		if (nums == null || nums.size() != 4){
			throw new IllegalArgumentException("Corner list not valid");
		}
		cornerNums = new LinkedList<>(nums);
	}

	@Override
	boolean checkBet(int number) {
		return cornerNums.contains(number);
	}

	@Override
	int getPossibleFields() {
		return 4;
	}

}