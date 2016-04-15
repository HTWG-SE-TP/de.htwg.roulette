package de.htwg.roulette.model.bets;

public class Douzaines extends AbstractBet {
	public enum Flag {
		Premier, Milieu, Dernier
	}

	private Flag dozenFlag;

	public Douzaines(int money, Flag f) {
		super(money);
		dozenFlag = f;
	}

	@Override
	boolean checkBet(int number) {
		switch (dozenFlag) {
		case Premier:
			return number < 13;
		case Milieu:
			return number > 12 && number < 25;
		case Dernier:
			return number > 24 && number < 37;
		}
		return false;
	}

	@Override
	int getPossibleFields() {
		return 12;
	}

}
