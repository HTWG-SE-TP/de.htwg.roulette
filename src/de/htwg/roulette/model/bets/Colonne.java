package de.htwg.roulette.model.bets;

public class Colonne extends AbstractBet {
	private int row;

	public Colonne(int money, int r) {
		super(money);
		assert(r > 33 && r < 37);
		row = r;

	}

	@Override
	boolean checkBet(int number) {
		switch (row) {
		case 34:
			return (number % 3 == 1); //Column 1
		case 35:
			return (number % 3 == 2); //Column 2
		case 36:
			return (number % 3 == 0); //Column 3
		}
		return false;
	}

	@Override
	int getPossibleFields() {
		return 12;

	}

}
