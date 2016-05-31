package de.htwg.util.Visitor;

import de.htwg.roulette.model.Account;
import de.htwg.roulette.model.User;
import de.htwg.util.observer.Observable;

public interface Visitor {
	public void visit(User player, Account bank, Observable observer, int number);
	public void visit(Account bank, int result);
}
