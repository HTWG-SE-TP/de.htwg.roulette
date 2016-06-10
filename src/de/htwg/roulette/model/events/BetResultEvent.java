package de.htwg.roulette.model.events;

import de.htwg.roulette.model.User;
import de.htwg.roulette.model.bets.AbstractBet;
import de.htwg.roulette.model.bets.IBet;
import de.htwg.util.observer.Event;

public class BetResultEvent implements Event {
	String resultInfo;

	public BetResultEvent(User p, IBet bet, int result) {
		String tmp;
		if (result >= 0) {
			tmp = "won";
		} else {
			tmp = "lost";
		}

		resultInfo = String.format("%s %s %d$ with his bet on %s.", p.getName(), tmp, result, bet.getName());
	}
	
	@Override
	public String toString(){
		return  resultInfo;
	}
}
