package de.htwg.roulette.model.bets;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class ChevalTest {
	
	Cheval cheval;
	
	@Before
	public void setUp() {
		cheval = new Cheval(100,1,2);
	}

	@Test
	public void testCheckBet() {
		assertEquals(true,cheval.checkBet(1));
		assertEquals(true,cheval.checkBet(2));
		assertEquals(false,cheval.checkBet(5));
		assertEquals(36/2, cheval.getPossibleFields());
	}

}
