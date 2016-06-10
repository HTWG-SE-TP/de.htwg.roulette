package de.htwg.roulette.model.events;

import de.htwg.util.observer.Event;

public class PlayerEvent implements Event {
	public String user;
	public boolean added;
	public boolean result;
	
	public PlayerEvent(String p, boolean add, boolean res) {
		user = p;
		added = add;
		result = res;
	}
}
