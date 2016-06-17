package de.htwg.util.Visitor;

import de.htwg.util.observer.Observable;

public interface Visitor {
	public void visit(Observable observer, int number);
}
