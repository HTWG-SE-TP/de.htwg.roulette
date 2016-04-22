package de.htwg.roulette.model.bets;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.roulette.model.bets.Dozen.Flag;

public class DozenTest {

	@Test
	public void Lower() {
		Dozen bet = new Dozen(50, Flag.Premier);
		 
		assertEquals(bet.getPossibleFields(), 12);
		
		 assertEquals(bet.checkBet(1), true);
		 assertEquals(bet.checkBet(17), false);
		 assertEquals(bet.checkBet(30), false);
		 assertEquals(bet.checkBet(99), false);
	}
	
	@Test
	public void Middle() {
		Dozen bet = new Dozen(50, Flag.Milieu);
		 
		 assertEquals(bet.checkBet(1), false);
		 assertEquals(bet.checkBet(17), true);
		 assertEquals(bet.checkBet(30), false);
		 assertEquals(bet.checkBet(99), false);
	}

	@Test
	public void Upper() {
		Dozen bet = new Dozen(50, Flag.Dernier);
		 
		 assertEquals(bet.checkBet(1), false);
		 assertEquals(bet.checkBet(17), false);
		 assertEquals(bet.checkBet(30), true);
		 assertEquals(bet.checkBet(99), false);
	}

}
