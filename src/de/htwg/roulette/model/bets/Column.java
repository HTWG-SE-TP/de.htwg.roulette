package de.htwg.roulette.model.bets;

public class Column extends AbstractBet {
	enum Rows {
		one,
		two,
		three
	}
	
	private Rows row;

	
	/**Column Bet
	 * @param money
	 * @param r Should be 34, 35 or 36
	 */
	public Column(int money, int r) {
		super(money);
		
		if (r == 34){
			row = Rows.one;
		} else if (r == 35)  {
			row = Rows.two;
		} else if (r == 36)  {
			row = Rows.three;
		} else {
			throw new IllegalArgumentException("Row number invalid");
		}

	}

	@Override
	boolean checkBet(int number) {
		if (!numInRange(number)) 
			return false;
		
		if (row == Rows.one) {
			return number % 3 == 1; 
			
		} else if (row == Rows.two) {
			return number % 3 == 2; 
			
		} else {
			return number % 3 == 0; 
		}
	}

	@Override
	int getPossibleFields() {
		return 12;

	}
	
	@Override
	public
	String getName() {
		return "a Column";
	}

}
