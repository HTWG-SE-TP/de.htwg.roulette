package de.htwg.roulette.model.events;

import de.htwg.util.observer.Event;

public class GuiLogEvent implements Event {
	private String txt;
	public GuiLogEvent(String text){
		txt = text;
	}
	
	@Override
	public String toString(){
		return txt;
	}
}
