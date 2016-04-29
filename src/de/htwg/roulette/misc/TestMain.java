package de.htwg.roulette.misc;

import de.htwg.roulette.controller.Controller;
import de.htwg.roulette.tui.TextUI;

public class TestMain {

	public TestMain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		TextUI tui = new TextUI(new Controller());
		tui.printUI();
		boolean quit = false;
		while (!quit) {
		    quit = tui.process();	
		}
	}

}
