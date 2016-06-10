package de.htwg.roulette.model.bets;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TwoRowsTest {

	@Test
	public void test() {
		TwoRows bet = new TwoRows(50, 2);

		assertEquals(6, bet.getPossibleFields());

		assertEquals(false, bet.checkBet(0));
		
		assertEquals(true, bet.checkBet(1));
		assertEquals(true, bet.checkBet(2));
		assertEquals(true, bet.checkBet(6));
		
		assertEquals(false, bet.checkBet(7));
		assertEquals(false, bet.checkBet(20));
		assertEquals(false, bet.checkBet(99));
		
		
		bet = new TwoRows(50, 20);
		assertEquals(false, bet.checkBet(0));
		
		assertEquals(false, bet.checkBet(18));
		assertEquals(true, bet.checkBet(19));
		assertEquals(true, bet.checkBet(20));
		assertEquals(true, bet.checkBet(24));
		
		assertEquals(false, bet.checkBet(25));
		assertEquals(false, bet.checkBet(99));
	}

}
