package com.encora.assignment.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.encora.assignment.model.Horse;

public class HorseDAO {
	private List<Horse> horseRecordList;

	public List<Horse> getHorseRecords() {
		//TODO - add some logic to fetch horse records from DB.
		if (horseRecordList == null) {
			horseRecordList = new ArrayList<>();
			horseRecordList.add(new Horse(1,"That Darn Gray Cat", 5, true));
			horseRecordList.add(new Horse(2,"Fort Utopia", 10, false));
			horseRecordList.add(new Horse(3,"Count Sheep", 9, false));
			horseRecordList.add(new Horse(4,"Ms Traitour", 4, false));
			horseRecordList.add(new Horse(5,"Real Princess", 3, false));
			horseRecordList.add(new Horse(6,"Pa Kettle", 5, false));
			horseRecordList.add(new Horse(7,"Gin Stinger", 6, false));
		}
		return horseRecordList;
	}
	
	public boolean setWinnerHorse(int horseNumber) {
		boolean update = false;
		//TODO - add some logic to set a horse as winner by provided horse number in DB.
		try {
			horseRecordList = getHorseRecords();
			Horse horseObject = horseRecordList.stream().filter(horse -> horse.getHorseNumber() == horseNumber).findAny().get();
			horseObject.setWinner(true);
			horseRecordList.stream().filter(horse -> horse.getHorseNumber() != horseNumber).forEach(horse -> horse.setWinner(false));
			update = true;
		} catch (NoSuchElementException e) {
			//place any logic for exception handling or do nothing at this step.
		}
		return update;
	}
	
	public Horse findHorseByHorseNumber(int horseNumber) {
		return getHorseRecords().stream().filter(horse -> horse.getHorseNumber() == horseNumber).findAny().orElse(null);
	}
}
