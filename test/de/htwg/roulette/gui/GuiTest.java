package de.htwg.roulette.gui;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.roulette.RouletteModule;
import de.htwg.roulette.controller.IController;
import de.htwg.util.observer.Observable;

public class GuiTest {

	private Gui gui;

	@Test
	public void test() throws Exception {
		// Init
		Observable mainObserver = new Observable();

		// Injection
		Injector injector = Guice.createInjector(new RouletteModule());
		IController controller = injector.getInstance(IController.class);
		controller.create(mainObserver, injector);

		// Starting gui
		gui = new Gui(controller, mainObserver);
	}


}
