package de.htwg.util.Visitor;

import de.htwg.roulette.model.Account;
import de.htwg.roulette.model.User;
import de.htwg.util.observer.Observable;

public interface Visitable {
	public void calculateResult(User player, Account bank, Observable observer, int number);
}
