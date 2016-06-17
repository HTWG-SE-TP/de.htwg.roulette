package de.htwg.roulette.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.htwg.roulette.model.bets.Black;
import de.htwg.util.observer.Observable;

public class ControllerTest {

	private Controller cont;

	@Before
	public void setUp() throws Exception {
		cont = new Controller(new Observable());
	}

	@Test
	public void test() {
		assertEquals(1, cont.getRound());

		cont.addPlayer("Test", 100);
		cont.addPlayer("Test", 100);
		cont.addPlayer("Test2", 100);

		assertEquals(2, cont.getPlayers().size());

		Black bet = new Black(10);
		cont.placeBet(null, bet);
		cont.placeBet("Test", null);
		cont.placeBet("WrongName", bet);
		assertEquals(0, cont.getBetCount());

		cont.placeBet("Test", new Black(-10));
		cont.placeBet("Test", new Black(200));
		// below table limit
		cont.placeBet("Test", new Black(5));
		assertEquals(0, cont.getBetCount());

		cont.placeBet("Test", bet);
		assertEquals(1, cont.getBetCount());

		cont.nextRound();
		assertEquals(2, cont.getRound());

		cont.removePlayer("WrongName");
		cont.removePlayer("Test");
		assertEquals(1, cont.getPlayers().size());

	}

}
