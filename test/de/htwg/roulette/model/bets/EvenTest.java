package de.htwg.roulette.model.bets;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EvenTest {

	private Even bet;
	
	@Before
	public void setUp() throws Exception {
		bet = new Even(50);
	}

	@Test
	public void test() {
		assertEquals(bet.getPossibleFields(), 18);
		assertEquals(bet.getName().isEmpty(), false);
		
		assertEquals(bet.checkBet(6), true);
		assertEquals(bet.checkBet(7), false);
		assertEquals(bet.checkBet(99), false);
	}

}
