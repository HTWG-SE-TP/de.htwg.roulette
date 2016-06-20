package de.htwg.roulette;

import com.google.inject.*;

import de.htwg.roulette.controller.Controller;
import de.htwg.roulette.controller.IController;

public class RouletteModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IController.class).to(Controller.class);
	}

}
