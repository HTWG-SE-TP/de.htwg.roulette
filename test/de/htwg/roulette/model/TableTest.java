package de.htwg.roulette.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.htwg.roulette.model.bets.Black;

public class TableTest {

	private Table table;
	
	@Before
	public void setUp() throws Exception {
		table = new Table(10, 100);
	}

	@Test
	public void test() {
		assertEquals(false, table.checkBet(new Black(0)));

		assertEquals(true, table.checkBet(new Black(10)));
		assertEquals(true, table.checkBet(new Black(50)));
		assertEquals(true, table.checkBet(new Black(100)));
		
		assertEquals(false, table.checkBet(new Black(200)));
	}

}
