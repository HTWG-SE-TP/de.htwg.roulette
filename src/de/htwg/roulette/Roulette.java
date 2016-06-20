package de.htwg.roulette;

import com.google.inject.*;

import de.htwg.roulette.controller.Controller;
import de.htwg.roulette.controller.IController;
import de.htwg.roulette.tui.TextUI;
import de.htwg.util.observer.Observable;

public class Roulette {
	
	private Roulette() {	
	}

	public static void main(String[] args) {
		//Init
		Observable mainObserver = new Observable();
		
		//Injection
		Injector injector = Guice.createInjector(new RouletteModule());
		IController controller = injector.getInstance(IController.class);
		controller.create(mainObserver);

		TextUI tui = new TextUI(controller);
		mainObserver.addObserver(tui);
		tui.printInitalUI();
		boolean quit = false;
		while (!quit) {
		    quit = tui.process();	
		}
	}

}
