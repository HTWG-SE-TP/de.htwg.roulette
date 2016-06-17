package de.htwg.roulette.model.bets;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class CornerTest {

	Corner bet;

	@Test
	public void General() {
		bet = new Corner(50, Arrays.asList(1, 2, 4, 5));
		assertEquals(bet.getPossibleFields(), 4);
		assertEquals(bet.getName().isEmpty(), false);

		boolean catched = false;
		try {
			bet = new Corner(50, Arrays.asList(1));
		} catch (Exception ex) {
			catched = true;
		}
		assertEquals(catched, true);

		catched = false;
		try {
			bet = new Corner(50, null);
		} catch (Exception ex) {
			catched = true;
		}
		assertEquals(catched, true);
		
		catched = false;
		try {
			bet = new Corner(50, Arrays.asList(99, 2, 4, 5));
		} catch (Exception ex) {
			catched = true;
		}
		assertEquals(catched, true);

		assertEquals(false, Corner.validate(Arrays.asList(99, 2, 4, 5)));
		assertEquals(false, Corner.validate(Arrays.asList(1, 99, 4, 5)));
		assertEquals(false, Corner.validate(Arrays.asList(1, 2, 99, 5)));
		assertEquals(false, Corner.validate(Arrays.asList(1, 2, 4, 99)));
	}

	@Test
	public void Test() {
		bet = new Corner(50, Arrays.asList(1, 2, 4, 5));

		assertEquals(bet.checkBet(1), true);
		assertEquals(bet.checkBet(2), true);
		assertEquals(bet.checkBet(3), false);

	}

}