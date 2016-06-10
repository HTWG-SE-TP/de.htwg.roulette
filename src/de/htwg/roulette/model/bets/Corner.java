package de.htwg.roulette.model.bets;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Corner extends AbstractBet{
	private List<Integer> cornerNums;

	public Corner(int money, List<Integer> nums) {
		super(money);
				
		if (nums == null || nums.size() != 4 || !validate(nums)){
			throw new IllegalArgumentException("Corner list not valid");
		}
		cornerNums = new LinkedList<>(nums);
	}
	
	public static boolean validate(List<Integer> nums){
		Collections.sort(nums);
		int min = nums.get(0);
		if(nums.get(1) != min+1 || nums.get(2) != min + 3 || nums.get(3) != min+4)
			return false;
		return true;
		
	}

	@Override
	boolean checkBet(int number) {
		return cornerNums.contains(number);
	}

	@Override
	int getPossibleFields() {
		return 4;
	}

	@Override
	public
	String getName() {
		return "a Corner";
	}
}