package de.htwg.roulette.model.bets;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ColumnTest {

	@Test
	public void General() {
		Column bet = new Column(50, 34);
		assertEquals(bet.getPossibleFields(), 12);
		assertEquals(bet.getName().isEmpty(), false);

		boolean catched = false;
		try {
			bet = new Column(50, 50);
		} catch (Exception ex) {
			catched = true;
		}
		assertEquals(catched, true);
	}

	@Test
	public void Row1() {
		Column bet = new Column(50, 34);

		assertEquals(bet.checkBet(1), true);
		assertEquals(bet.checkBet(17), false);
		assertEquals(bet.checkBet(30), false);
		assertEquals(bet.checkBet(99), false);
	}

	@Test
	public void Row2() {
		Column bet = new Column(50, 35);

		assertEquals(bet.checkBet(1), false);
		assertEquals(bet.checkBet(17), true);
		assertEquals(bet.checkBet(30), false);
	}

	@Test
	public void Row3() {
		Column bet = new Column(50, 36);

		assertEquals(bet.checkBet(1), false);
		assertEquals(bet.checkBet(17), false);
		assertEquals(bet.checkBet(30), true);
	}

}
