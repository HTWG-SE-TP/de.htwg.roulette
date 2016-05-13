package de.htwg.roulette;

import java.util.Observer;

import de.htwg.roulette.controller.Controller;
import de.htwg.roulette.tui.TextUI;
import de.htwg.util.observer.Observable;

public class Roulette {
	
	private Roulette() {	
	}

	public static void main(String[] args) {
		//Init
		Observable mainObserver = new Observable();
		TextUI tui = new TextUI(new Controller(mainObserver));
		mainObserver.addObserver(tui);
		tui.printUI();
		boolean quit = false;
		while (!quit) {
		    quit = tui.process();	
		}
	}

}