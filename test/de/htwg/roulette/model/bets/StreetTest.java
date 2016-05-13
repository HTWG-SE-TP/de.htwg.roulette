package de.htwg.roulette.model.bets;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StreetTest {

	@Test
	public void test() {
		Street bet = new Street(50, 2);

		assertEquals(3, bet.getPossibleFields());
		assertEquals(bet.getName().isEmpty(), false);

		assertEquals(false, bet.checkBet(0));
		
		assertEquals(true, bet.checkBet(1));
		assertEquals(true, bet.checkBet(2));
		assertEquals(true, bet.checkBet(3));
		
		assertEquals(false, bet.checkBet(4));
		assertEquals(false, bet.checkBet(20));
		assertEquals(false, bet.checkBet(99));
		
		
		bet = new Street(50, 20);
		assertEquals(false, bet.checkBet(0));
		
		assertEquals(false, bet.checkBet(18));
		assertEquals(true, bet.checkBet(19));
		assertEquals(true, bet.checkBet(20));
		assertEquals(true, bet.checkBet(21));
		
		assertEquals(false, bet.checkBet(22));
		assertEquals(false, bet.checkBet(99));
	}

}
