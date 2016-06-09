package de.htwg.roulette.model.bets;

public abstract class AbstractBet implements IBet {	
	public AbstractBet(int money) {
		setStake(money);
	}
	
	abstract boolean checkBet(int number);
	abstract int getPossibleFields();
	public abstract String getName();
	
	private int stake;
 	
	public int getStake() {
		return stake;
	}

	public void setStake(int stake) {
		this.stake = stake;
	}
	
	public int betResult(int number){
		boolean result = checkBet(number);
		
		if (result){
			return stake + stake * getQuote();
		} else {
			return -stake;
		}
	}
	
	public int getQuote(){
		return 36/getPossibleFields() - 1;
	}
	
	protected boolean numInRange(int num){
		return (num >= 0) && (num <= 36);
	}
	
	@Override
	public String toString(){
		return String.format("%s -- Stake: %d, Quote: %d", getName(), getStake(), getQuote());
	}
}
