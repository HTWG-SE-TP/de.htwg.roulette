package de.htwg.roulette;

import java.util.Observer;

import de.htwg.roulette.controller.Controller;
import de.htwg.roulette.tui.TextUI;

public class Roulette {
	
	private Roulette() {	
	}

	public static void main(String[] args) {
		//Init
		TextUI tui = new TextUI(new Controller(null));
		tui.printUI();
		boolean quit = false;
		while (!quit) {
		    quit = tui.process();	
		}
	}

}
