package de.htwg.roulette.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;

import de.htwg.roulette.RouletteModule;
import de.htwg.roulette.model.bets.Black;
import de.htwg.util.observer.Observable;

public class ControllerTest {

	private IController cont;

	@Before
	public void setUp() throws Exception {
		cont = new Controller();
		cont.create(new Observable(), Guice.createInjector(new RouletteModule()));
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
		cont.placeBet("", bet);
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
