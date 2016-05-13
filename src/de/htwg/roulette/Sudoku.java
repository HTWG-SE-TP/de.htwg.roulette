package de.htwg.roulette;

import de.htwg.roulette.controller.Controller;
import de.htwg.roulette.tui.TextUI;

public class Sudoku {
	
	private Sudoku() {	
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
