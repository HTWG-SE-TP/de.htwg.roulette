package de.htwg.roulette.model.events;

import de.htwg.roulette.model.bets.IBet;
import de.htwg.util.observer.Event;

public class BetAddedEvent implements Event {
	private final String user;
	private final IBet bet;
	private final boolean result;
	
	public BetAddedEvent(String p, IBet newBet, boolean res) {
		user = p;
		bet = newBet;
		result = res;
	}

	public String getUser() {
		return user;
	}

	public IBet getBet() {
		return bet;
	}

	public boolean getResult() {
		return result;
	}
}
