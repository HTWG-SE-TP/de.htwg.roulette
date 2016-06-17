package de.htwg.roulette.model.events;

import de.htwg.roulette.model.bets.IBet;
import de.htwg.util.observer.Event;

public class BetResultEvent implements Event {
	public String user;
	public IBet bet;
	public int result;

	public BetResultEvent(String p, IBet newBet, int result) {
		user = p;
		bet = newBet;
		this.result = result;
	}
}
