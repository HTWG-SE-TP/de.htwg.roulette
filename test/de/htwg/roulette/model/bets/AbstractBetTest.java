package de.htwg.roulette.model.bets;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AbstractBetTest {
	SingleNumber bet;
	
	@Before
	public void setUp() throws Exception {
		bet = new SingleNumber(500, 3);
	}

	@Test
	public void test() {
		assertEquals(bet.getStake(), 500);
		bet.setStake(1000);
		assertEquals(bet.getStake(), 1000);
		
		assertEquals(bet.getQuote(), 35);
		
		assertEquals(bet.betResult(3), 1000 + 1000 * 35);
		
		assertEquals(bet.betResult(5), -1000);
		
		assertEquals(bet.numInRange(-5), false);
		assertEquals(bet.numInRange(0), true);
		assertEquals(bet.numInRange(5), true);
		assertEquals(bet.numInRange(36), true);
		assertEquals(bet.numInRange(37), false);

		bet.toString();
	}

}
