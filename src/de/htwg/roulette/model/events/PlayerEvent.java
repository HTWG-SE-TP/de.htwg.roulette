package de.htwg.roulette.model.events;

import de.htwg.util.observer.Event;

public class PlayerEvent implements Event {
	private final String user;
	private final boolean added;
	private final boolean result;
	
	public PlayerEvent(String p, boolean add, boolean res) {
		user = p;
		added = add;
		result = res;
	}

	public boolean getResult() {
		return result;
	}

	public boolean isAdded() {
		return added;
	}

	public String getUser() {
		return user;
	}
}
