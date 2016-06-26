package de.htwg.roulette;

import com.google.inject.*;

import de.htwg.roulette.controller.Controller;
import de.htwg.roulette.controller.IController;
import de.htwg.roulette.gui.Gui;
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
		controller.create(mainObserver, injector);

		//Starting gui
		Gui gui = new Gui(controller, mainObserver);
		
		//Starting tui
		TextUI tui = new TextUI(controller);
		mainObserver.addObserver(tui);
		tui.printInitalUI();
		
		//Inital Players, just for the comfort
		controller.addPlayer("Henry", 100000);
		controller.addPlayer("Marc", 1000);
		
		boolean quit = false;
		while (!quit) {
		    quit = tui.process();	
		}
	}

}
