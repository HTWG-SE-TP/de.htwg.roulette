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

		assertEquals(true, cont.addPlayer("Test", 100));
		assertEquals(false, cont.addPlayer("Test", 100));
		assertEquals(true, cont.addPlayer("Test2", 100));
		
		assertEquals(2, cont.getPlayers().size());
		
		Black bet = new Black(10);
		assertEquals(false, cont.placeBet(null, bet));
		assertEquals(false, cont.placeBet("Test", null));
		assertEquals(false, cont.placeBet("WrongName", bet));
		
		assertEquals(false, cont.placeBet("Test", new Black(-10)));
		assertEquals(false, cont.placeBet("Test", new Black(200)));
		//below table limit
		assertEquals(false, cont.placeBet("Test", new Black(5)));
		
		assertEquals(true, cont.placeBet("Test", bet));
		
		cont.nextRound();		
		assertEquals(2, cont.getRound());
		
		
		assertEquals(false, cont.removePlayer("WrongName"));
		assertEquals(true, cont.removePlayer("Test"));
		assertEquals(1, cont.getPlayers().size());
	
	}

}
