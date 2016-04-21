package de.htwg.roulette.model.bets;

import static org.junit.Assert.*;

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
		
		bet.toString();
	}

}
