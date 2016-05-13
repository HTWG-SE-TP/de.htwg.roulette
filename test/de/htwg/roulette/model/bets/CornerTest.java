package de.htwg.roulette.model.bets;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class CornerTest {

	@Test
	public void General(){
		 Corner bet = new Corner(50, Arrays.asList(1, 2, 3, 4));
		 assertEquals(bet.getPossibleFields(), 4);
		 assertEquals(bet.getName().isEmpty(), false);
		 
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
		 Column bet = new Column(50, 34);
		 
		 assertEquals(bet.checkBet(1), true);
		 assertEquals(bet.checkBet(17), false);
		 assertEquals(bet.checkBet(30), false);
		 assertEquals(bet.checkBet(99), false);
	}
	
	@Test
	public void Row2() {
		 Column bet = new Column(50, 35);
		 
		 assertEquals(bet.checkBet(1), false);
		 assertEquals(bet.checkBet(17), true);
		 assertEquals(bet.checkBet(30), false);
	}

	@Test
	public void Row3() {
		 Column bet = new Column(50, 36);
		 
		 assertEquals(bet.checkBet(1), false);
		 assertEquals(bet.checkBet(17), false);
		 assertEquals(bet.checkBet(30), true);
	}

}
