package com.encora.assignment.dao;

import java.util.ArrayList;
import java.util.List;

import com.encora.assignment.model.Inventory;

public class InventoryDAO {
	private static final int defaultQuantityOfDenomination = 10;
	private List<Inventory> inventoryRecordList;

	public List<Inventory> getInventoryRecords() {
		//TODO - add some logic to fetch inventory records from DB.
		if (inventoryRecordList == null) {
			inventoryRecordList = new ArrayList<>();
			inventoryRecordList.add(new Inventory(1,10));
			inventoryRecordList.add(new Inventory(5,10));
			inventoryRecordList.add(new Inventory(10,10));
			inventoryRecordList.add(new Inventory(20,10));
			inventoryRecordList.add(new Inventory(100,10));
		}
		return inventoryRecordList;
	}
	
	public boolean resetInventoryRecords() {
		boolean update = false;
		//TODO - add some logic to reset inventory records quantity in DB.
		inventoryRecordList.forEach(inventory -> inventory.setQuantity(defaultQuantityOfDenomination));
		update = true;
		return update;
	}
}
