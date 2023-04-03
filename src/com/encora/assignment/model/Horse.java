package com.encora.assignment.model;

public class Horse {
	int horseNumber;
	String horseName;
	int odds;
	boolean isWinner;
	
	public Horse(int horseNumber, String horseName, int odds, boolean isWinner) {
		super();
		this.horseNumber = horseNumber;
		this.horseName = horseName;
		this.odds = odds;
		this.isWinner = isWinner;
	}

	public int getHorseNumber() {
		return horseNumber;
	}

	public String getHorseName() {
		return horseName;
	}

	public int getOdds() {
		return odds;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	@Override
	public String toString() {
		return horseNumber + "," + horseName + "," + odds + ","	+ (isWinner?"won":"lost");
	}
}
