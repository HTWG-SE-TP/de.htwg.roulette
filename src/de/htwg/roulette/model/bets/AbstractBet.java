package de.htwg.roulette.model.bets;

public abstract class AbstractBet {	
	public AbstractBet(int money) {
		setStake(money);
	}
	
	abstract int getPossibleFields();
	protected int stake;
	protected int possibleFields; 
	
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
	
	abstract boolean checkBet(int number);
	
	public int getQuote(){
		return 36/getPossibleFields() - 1;
	}
	
	protected boolean numInRange(int num){
		return (num >= 0) && (num <= 36);
	}
	
	@Override
	public String toString(){
		return String.format("AbstractBet -- Stake: %d, Quote: %d", getStake(), getQuote());
	}
}
