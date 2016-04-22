package de.htwg.roulette.model.bets;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OddTest {

	private Odd bet;
	
	@Before
	public void setUp() throws Exception {
		bet = new Odd(50);
	}

	@Test
	public void test() {
		assertEquals(bet.getPossibleFields(), 18);
		assertEquals(bet.checkBet(6), false);
		assertEquals(bet.checkBet(7), true);
		assertEquals(bet.checkBet(99), false);
	}

}