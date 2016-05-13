package de.htwg.roulette.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.htwg.roulette.model.User;
import de.htwg.roulette.model.bets.Black;

public class BetResultEventTest {

	private BetResultEvent event;


	@Test
	public void test() {
		event = new BetResultEvent(new User("", 50), new Black(50), 10);
		assertEquals(false, event.toString().isEmpty());
		
		event = new BetResultEvent(new User("", 50), new Black(50), -10);
		assertEquals(false, event.toString().isEmpty());
	}

}
