package de.htwg.roulette.tui;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.roulette.RouletteModule;
import de.htwg.roulette.controller.IController;
import de.htwg.util.observer.Observable;

public class TuiTest {

	private TextUI tui;

	@Test
	public void test() throws Exception {
		// Init
		Observable mainObserver = new Observable();

		// Injection
		Injector injector = Guice.createInjector(new RouletteModule());
		IController controller = injector.getInstance(IController.class);
		controller.create(mainObserver, injector);

		// Starting gui
		tui = new TextUI(controller);
		
		tui.printInitalUI();
		
		assertEquals(false, tui.process("help"));
		assertEquals(false, tui.process("add test 100"));
		assertEquals(false, tui.process("nr"));
		
		assertEquals(false, tui.process("lololol"));
		assertEquals(false, tui.process(""));
		
	
	
		
		assertEquals(false, tui.process("remove test"));
	
		
		
	
		
		assertEquals(true, tui.process("quit"));
	}


}
