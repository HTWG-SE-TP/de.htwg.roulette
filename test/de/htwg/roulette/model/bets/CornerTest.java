package de.htwg.roulette.model.bets;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class CornerTest {

	@Test
	public void General(){
		 Corner bet = new Corner(50, Arrays.asList(1, 2, 3, 4));
		 assertEquals(bet.getPossibleFields(), 4);
		 
		 boolean catched = false;
		 try {
			 bet = new Corner(50, Arrays.asList(1)); 
		 } catch (Exception ex){
			 catched = true;
		 }
		 assertEquals(catched, true);
		 
		 catched = false;
		 try {
			 bet = new Corner(50, null); 
		 } catch (Exception ex){
			 catched = true;
		 }
		 assertEquals(catched, true);
	}
	
	@Test
	public void Row1() {
		Corner bet = new Corner(50, Arrays.asList(1, 2, 4, 5));
		 
		 assertEquals(bet.checkBet(1), true);
		 assertEquals(bet.checkBet(2), true);
		 assertEquals(bet.checkBet(-5), false);
		 assertEquals(bet.checkBet(99), false);
	}
	
	@Test
	public void Row2() {
		Corner bet = new Corner(50, Arrays.asList(13, 14, 16, 17));
		
		 assertEquals(bet.checkBet(-5), false);
		 assertEquals(bet.checkBet(13), true);
		 assertEquals(bet.checkBet(16), true);
		 assertEquals(bet.checkBet(99), false);
	}



}
