package de.htwg.roulette.controller;

import de.htwg.util.observer.Event;

public class NextRoundEvent implements Event {
	private int num;
	public NextRoundEvent(int num){
		this.num = num;
	}
	
	public int getResult(){
		return num;
	}
}
