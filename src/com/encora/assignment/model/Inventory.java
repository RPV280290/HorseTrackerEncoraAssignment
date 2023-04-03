package com.encora.assignment.model;

public class Inventory {
	private int denomination;
	private int quantity;
	public Inventory(int denomination, int quantity) {
		super();
		this.denomination = denomination;
		this.quantity = quantity;
	}
	
	public int getDenomination() {
		return denomination;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "$" + denomination + "," + quantity;
	}
}
