package de.htwg.roulette.model.events;

import de.htwg.roulette.model.bets.IBet;
import de.htwg.util.observer.Event;

public class BetAddedEvent implements Event {
	public final String user;
	public final IBet bet;
	public final boolean result;
	
	public BetAddedEvent(String p, IBet newBet, boolean res) {
		user = p;
		bet = newBet;
		result = res;
	}
}
