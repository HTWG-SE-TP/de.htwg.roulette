package de.htwg.roulette.model.bets;

public class Dozen extends AbstractBet {
	public enum Flag {
		Premier, Milieu, Dernier
	}

	private Flag dozenFlag;

	public Dozen(int money, Flag f) {
		super(money);
		dozenFlag = f;
	}

	@Override
	boolean checkBet(int number) {
		if (dozenFlag == Flag.Premier){
			return number < 13;
		} else if (dozenFlag == Flag.Milieu) {
			return number > 12 && number < 25;
		} else {
			return number > 24 && number < 37;
		}
	}

	@Override
	int getPossibleFields() {
		return 12;
	}

}
