package de.htwg.roulette;

import com.google.inject.*;

import de.htwg.roulette.controller.Controller;
import de.htwg.roulette.controller.IController;
import de.htwg.roulette.model.IUser;
import de.htwg.roulette.model.User;

public class RouletteModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IController.class).to(Controller.class);
		bind(IUser.class).to(User.class);
	}

}
