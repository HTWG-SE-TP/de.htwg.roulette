package de.htwg.roulette.model.bets;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SplitTest {

	Split bet;

	@Before
	public void setUp() {
		bet = new Split(100, 1, 2);
	}

	@Test
	public void testCheckBet() {
		assertEquals(true, bet.checkBet(1));
		assertEquals(true, bet.checkBet(2));
		assertEquals(false, bet.checkBet(5));
		assertEquals(36 / 2, bet.getPossibleFields());
		assertEquals(bet.getName().isEmpty(), false);
	}

}
