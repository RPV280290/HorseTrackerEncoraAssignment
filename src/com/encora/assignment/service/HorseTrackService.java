package com.encora.assignment.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.encora.assignment.model.Horse;
import com.encora.assignment.model.Inventory;

public class HorseTrackService {
	private InventoryService inventoryService;
	private HorseService horseService;
	
	public void processHorseTrackUserInput (String userInput) {
		Pattern pattern = Pattern.compile("\\d+");
		String[] command = userInput.split(" ");
		if(userInput.equals("r")) {
			getInventoryService().resetInventoryRecords();
			printInventoryAndHorses();
		} else if (command.length != 2) {
			System.out.println("Invalid Command: " + userInput);
		} else if (command[0].equals("w")) {
			if(pattern.matcher(command[1]).matches()) {
				if (getHorseService().setWinnerHorse(Integer.parseInt(command[1]))) {
					printInventoryAndHorses();
				} else {
					System.out.println("Invalid Horse Number: " + command[1]);
				}
			} else {
				System.out.println("Invalid Horse Number: " + command[1]);
			}
		} else if (pattern.matcher(command[0]).matches()) {
			if(!command[1].equals("0") && pattern.matcher(command[1]).matches()) {
				if(getHorseService().isHorsePresent(Integer.parseInt(command[0]))) {
					playBet(Integer.parseInt(command[0]), Integer.parseInt(command[1]));
				} else {
					System.out.println("Invalid Horse Number: " + command[0]);
				}
			} else {
				System.out.println("Invalid Bet: " + command[1]);
			}
		} else {
			System.out.println("Invalid Command: " + userInput);
		}
	}
	
	public void playBet(int horseNumber, int betAmount) {
		Horse horse = getHorseService().findHorseByHorseNumber(horseNumber);
		if(horse.isWinner()) {
			int winningAmount = betAmount * horse.getOdds();
			int totalInventoryAmount = getInventoryService().getTotalInventoryAmount();
			if (winningAmount <= totalInventoryAmount) {
				int remainingAmount = winningAmount;
				List<Inventory> dispenseInventory = new ArrayList<>();
				List<Inventory> inventoryRecords = getInventoryService().getInventoryRecords();
				inventoryRecords = getInventoryListSorted(inventoryRecords, false);
				for(Inventory inventory: inventoryRecords) {
					int count = 0;
					int denomination = inventory.getDenomination();
					int quantity = inventory.getQuantity();
					while (quantity > 0 && remainingAmount > 0 && remainingAmount - denomination >= 0) {
						remainingAmount = remainingAmount - denomination;
						count++;
						quantity--;
					}
					dispenseInventory.add(new Inventory(denomination, count));
					inventory.setQuantity(quantity);
				};
				System.out.println("Payout: " + horse.getHorseName() + "," + winningAmount);
				System.out.println("Dispensing:");
				getInventoryListSorted(dispenseInventory, true)
				.forEach(System.out::println);
				printInventoryAndHorses();
			} else {
				System.out.println("Insufficient Funds: " + winningAmount);
			}
		} else {
			System.out.println("No Payout: " + horse.getHorseName());
		}
	}
		
	public void printInventoryAndHorses() {
		//Fetching Inventory records
		System.out.println("Inventory:");
		getInventoryService().getInventoryRecords().forEach(System.out::println);
				
		//Fetching Horse records
		System.out.println("Horses:");
		getHorseService().getHorseRecords().forEach(System.out::println);
	}
	
	private List<Inventory> getInventoryListSorted(List<Inventory> inventoryList, boolean asc) {
		Comparator<Inventory> comparator = asc?(o1, o2) -> o1.getDenomination() - o2.getDenomination()
											  :(o1, o2) -> o2.getDenomination() - o1.getDenomination();
		return inventoryList.stream().sorted(comparator).collect(Collectors.toList());
			
	}
	
	private InventoryService getInventoryService() {
		if (inventoryService == null) {
			inventoryService = new InventoryService();
		}
		return inventoryService;
	}
	
	private HorseService getHorseService() {
		if (horseService == null) {
			horseService = new HorseService();
		}
		return horseService;
	}
}
