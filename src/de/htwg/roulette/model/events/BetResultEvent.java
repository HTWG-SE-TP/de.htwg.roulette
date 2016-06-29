package de.htwg.roulette.model.events;

import de.htwg.roulette.model.bets.IBet;
import de.htwg.util.observer.Event;

public class BetResultEvent implements Event {
	private String user;
	private IBet bet;
	private int result;

	public BetResultEvent(String p, IBet newBet, int result) {
		user = p;
		bet = newBet;
		this.result = result;
	}
	
	@Override
	public String toString(){
		String tmp;
		if (result >= 0) {
			tmp = "won";
		} else {
			tmp = "lost";
		}

		String resultInfo = String.format("%s %s %d$ with his bet on %s.", user, tmp, result,
				bet.getName());
		return resultInfo;
	}
}
