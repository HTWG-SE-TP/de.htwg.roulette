package de.htwg.roulette.controller;

import de.htwg.roulette.model.User;
import de.htwg.roulette.model.bets.AbstractBet;
import de.htwg.util.observer.Event;

public class BetResultEvent implements Event {
	String resultInfo;

	public BetResultEvent(User p, AbstractBet bet, int result) {
		String tmp;
		if (result >= 0) {
			tmp = "won";
		} else {
			tmp = "lost";
		}

		resultInfo = String.format("%s %s %d$ with his bet on %s.", p.getName(), tmp, result, bet.getName());
	}
	
	public String toString(){
		return  resultInfo;
	}
}
