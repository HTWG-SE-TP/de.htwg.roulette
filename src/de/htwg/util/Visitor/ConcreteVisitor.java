package de.htwg.util.Visitor;

import de.htwg.roulette.controller.BetResultEvent;
import de.htwg.roulette.model.Account;
import de.htwg.roulette.model.User;
import de.htwg.roulette.model.bets.AbstractBet;
import de.htwg.util.observer.Observable;

public class ConcreteVisitor implements Visitor {

	@Override
	public void visit(User player, Account bank, Observable observer, int number) {
		int ball = player.getBalance();
		for (AbstractBet bet : player.getBets()) {
			int result = bet.betResult(number);
			BetResultEvent event = new BetResultEvent(player, bet, result);				
			observer.notifyObservers(event);
			
			visit(bank, result);
			ball += result;
		}
		player.clearBets();
		player.setBalance(ball);

	}

	@Override
	public void visit(Account bank, int result) {
		bank.setBalance(bank.getBalance() - result); // update bank's balance
		
	}

}
