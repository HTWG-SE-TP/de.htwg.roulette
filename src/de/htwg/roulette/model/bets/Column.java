package de.htwg.roulette.model.bets;

public class Column extends AbstractBet {
	enum Rows {
		One,
		Two,
		Three
	}
	
	private Rows row;

	
	/**Column Bet
	 * @param money
	 * @param r Should be 34, 35 or 36
	 */
	public Column(int money, int r) {
		super(money);
		
		if (r == 34){
			row = Rows.One;
		} else if (r == 35)  {
			row = Rows.Two;
		} else if (r == 36)  {
			row = Rows.Three;
		} else {
			throw new IllegalArgumentException("Row number invalid");
		}

	}

	@Override
	boolean checkBet(int number) {
		if (row == Rows.One) {
			return (number % 3 == 1); 
			
		} else if (row == Rows.Two) {
			return (number % 3 == 2); 
			
		} else {
			return (number % 3 == 0); 
		}
	}

	@Override
	int getPossibleFields() {
		return 12;

	}

}
