package de.htwg.roulette.model;

public class Account {
	private String name;
	private int balance;
	
	public Account(String newName, int newBalance){
		setName(newName);
		setBalance(newBalance);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
}
