package de.htwg.roulette.model.bets;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FirstFourTest {

	
	
	@Test
	public void test() {
		FirstFour bet = new FirstFour(50);
		assertEquals(4, bet.getPossibleFields());
		assertEquals(bet.getName().isEmpty(), false);
		
		assertEquals(true, bet.checkBet(3));
		assertEquals(false, bet.checkBet(36));
		assertEquals(false, bet.checkBet(50));
		assertEquals(false, bet.checkBet(-7));
	}

}
