package de.htwg.roulette.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.htwg.roulette.model.bets.Black;

public class AccountTest {

	private User user;
	
	@Before
	public void setUp() throws Exception {
		user = new User("Test", 10);
	}

	@Test
	public void test() {
		user.setName("Name");
		assertEquals("Name", user.getName());

		user.setBalance(50);
		assertEquals(50, user.getBalance());
		
		user.addBet(new Black(50));
		assertEquals(1, user.getBets().size());
		
		user.clearBets();
		assertEquals(0, user.getBets().size());
	}

}
