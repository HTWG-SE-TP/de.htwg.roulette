package de.htwg.roulette.model.bets;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RedTest {

	private Red bet;
	
	@Before
	public void setUp() throws Exception {
		bet = new Red(50);
	}

	@Test
	public void test() {
		assertEquals(bet.getPossibleFields(), 18);
		assertEquals(bet.checkBet(3), true);
		assertEquals(bet.checkBet(4), false);
		assertEquals(bet.checkBet(99), false);
	}

}