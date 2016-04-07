package de.htwg.roulette.model.bets;

public abstract class AbstractBet {
	protected int stake;
	protected int possibleFields; 
	
	public AbstractBet(int money) {
		setStake(money);
	}

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
		return (36/possibleFields - 1);
	}
}
