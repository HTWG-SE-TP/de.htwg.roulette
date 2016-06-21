package de.htwg.roulette.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.htwg.roulette.model.bets.Black;
import de.htwg.roulette.model.events.BetAddedEvent;
import de.htwg.roulette.model.events.BetResultEvent;
import de.htwg.roulette.model.events.NextRoundEvent;
import de.htwg.roulette.model.events.PlayerEvent;
import de.htwg.util.observer.Event;

public class EventTest {

	private Event event;


	@Test
	public void test() {
		Account bank = new Account("LOL", 5000);
		event = new BetResultEvent("", new Black(50), 10);
		event = new BetAddedEvent("", new Black(50), true);
		event = new PlayerEvent("", true, true);
		NextRoundEvent nr = new NextRoundEvent(1);
		assertEquals(1, nr.getRolledNo());
	}

}
