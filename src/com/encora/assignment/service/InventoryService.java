package com.encora.assignment.service;

import java.util.List;

import com.encora.assignment.dao.InventoryDAO;
import com.encora.assignment.model.Inventory;

/** 
 * 
 * @author Ravi Prakash Verma
 * This Class has logic to call InventoryDAO(Data Access Object) class in order to fetch inventory records
 *
 */
public class InventoryService {
	InventoryDAO inventoryDAO;
	public List<Inventory> getInventoryRecords() {
		return getInventoryDAO().getInventoryRecords();
	}
	
	public boolean resetInventoryRecords() {
		return getInventoryDAO().resetInventoryRecords();
	}
	
	public int getTotalInventoryAmount() {
		 return getInventoryDAO().getInventoryRecords().stream()
				.map(inventory -> (inventory.getDenomination() * inventory.getQuantity()))
				.reduce(0, (a,b) -> (a+b));
	}
	
	private InventoryDAO getInventoryDAO() {
		if (inventoryDAO == null) {
			inventoryDAO = new InventoryDAO();
		}
		return inventoryDAO;
	}
}
