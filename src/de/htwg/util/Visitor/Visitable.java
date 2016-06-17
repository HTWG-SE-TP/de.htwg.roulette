package de.htwg.util.Visitor;


import de.htwg.util.observer.Observable;

public interface Visitable {
	public void calculateResult(Visitor player, Observable observer, int number);
}
