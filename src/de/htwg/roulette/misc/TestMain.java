package de.htwg.roulette.misc;

import de.htwg.roulette.controller.Controller;
import de.htwg.roulette.tui.TextUI;

public class TestMain {
	
	private TestMain() {	
	}

	public static void main(String[] args) {
		//Init
		TextUI tui = new TextUI(new Controller());
		tui.printUI();
		boolean quit = false;
		while (!quit) {
		    quit = tui.process();	
		}
	}

}
