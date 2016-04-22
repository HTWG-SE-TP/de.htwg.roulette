package de.htwg.roulette.model.bets;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.roulette.model.bets.Dozen.Flag;

public class SingeNumberTest {

	@Test
	public void test() {
		SingleNumber bet = new SingleNumber(50, 0);

		assertEquals(1, bet.getPossibleFields());

		assertEquals(true, bet.checkBet(0));
		assertEquals(false, bet.checkBet(17));
		assertEquals(false, bet.checkBet(30));
		assertEquals(false, bet.checkBet(99));
	}

}
