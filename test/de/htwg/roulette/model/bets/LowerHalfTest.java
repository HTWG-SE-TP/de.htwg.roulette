package de.htwg.roulette.model.bets;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LowerHalfTest {

	private LowerHalf bet;

	@Before
	public void setUp() throws Exception {
		bet = new LowerHalf(50);
	}

	@Test
	public void test() {
		assertEquals(bet.getPossibleFields(), 18);
		assertEquals(bet.getName().isEmpty(), false);
		
		assertEquals(false, bet.checkBet(-5));
		assertEquals(false, bet.checkBet(0));
		
		assertEquals(true, bet.checkBet(5));
		assertEquals(true, bet.checkBet(18));

		assertEquals(false, bet.checkBet(36));
		assertEquals(false, bet.checkBet(99));
	}

}
