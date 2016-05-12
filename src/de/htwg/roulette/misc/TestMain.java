package de.htwg.roulette.misc;

import de.htwg.roulette.controller.Controller;
import de.htwg.roulette.tui.TextUI;

public class TestMain {

	public TestMain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		//Single line formatting on console output
		//System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
		System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%n");
		
		//Init
		TextUI tui = new TextUI(new Controller());
		tui.printUI();
		boolean quit = false;
		while (!quit) {
		    quit = tui.process();	
		}
	}

}
