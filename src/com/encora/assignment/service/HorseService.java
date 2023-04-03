package com.encora.assignment.service;

import java.util.List;

import com.encora.assignment.dao.HorseDAO;
import com.encora.assignment.model.Horse;

/** 
 * 
 * @author Ravi Prakash Verma
 * This Class has logic to call HorseDAO(Data Access Object) class in order to fetch horse records
 *
 */
public class HorseService {
	private HorseDAO horseDAO;
	
	public List<Horse> getHorseRecords() {
		return getHorseDAO().getHorseRecords();
	}
	
	public boolean setWinnerHorse(int horseNumber) {
		return getHorseDAO().setWinnerHorse(horseNumber);
	}
	
	public boolean isHorsePresent(int horseNumber) {
		return getHorseDAO().findHorseByHorseNumber(horseNumber) != null;
	}
	
	public boolean isWinnerHorse(int horseNumber) {
		return getHorseDAO().findHorseByHorseNumber(horseNumber).isWinner();
	}
	
	public Horse findHorseByHorseNumber(int horseNumber) {
		return getHorseDAO().findHorseByHorseNumber(horseNumber);
	}
	
	private HorseDAO getHorseDAO() {
		if (horseDAO == null) {
			horseDAO = new HorseDAO();
		}
		return horseDAO;
	}
}
