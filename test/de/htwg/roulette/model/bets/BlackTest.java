package de.htwg.roulette.model.bets;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BlackTest {

	private Black bet;
	
	@Before
	public void setUp() throws Exception {
		bet = new Black(50);
	}

	@Test
	public void test() {
		assertEquals(bet.getPossibleFields(), 17);
		assertEquals(bet.checkBet(2), true);
		assertEquals(bet.checkBet(99), false);

		assertEquals(bet.getName().isEmpty(), false);
	}

}
