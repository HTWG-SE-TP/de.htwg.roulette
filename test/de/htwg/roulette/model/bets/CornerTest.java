package de.htwg.roulette.model.bets;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class CornerTest {
	
	Corner bet;
	
	
	
	@Test
	public void General(){
		 bet = new Corner(50, Arrays.asList(1, 2, 3, 4));
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
	public void Test() {
		 bet = new Corner(50, Arrays.asList(1, 2, 3, 4));
		 
		 assertEquals(bet.checkBet(1), true);
		 assertEquals(bet.checkBet(4), true);
		 assertEquals(bet.checkBet(5), false);
	}
	


}
