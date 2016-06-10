package de.htwg.roulette.model.events;

import de.htwg.roulette.model.bets.IBet;
import de.htwg.util.observer.Event;

public class BetAddedEvent implements Event {
	public String user;
	public IBet bet;
	public boolean result;
	
	public BetAddedEvent(String p, IBet newBet, boolean res) {
		user = p;
		bet = newBet;
		result = res;
	}
}
