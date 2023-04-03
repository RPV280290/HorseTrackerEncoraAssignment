package com.encora.assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.encora.assignment.service.HorseTrackService;

public class HorseTrackProgram {
	private static HorseTrackService horseTrackService;
	
	public static void main(String[] args) throws IOException {
		//System.out.println("Welcome To Horse Track Program");
		//System.out.println("--------------------------------");
		
		getHorseTrackService().printInventoryAndHorses();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String userInput = br.readLine().trim().toLowerCase();
		while(!userInput.equalsIgnoreCase("q")) {
			//System.out.println("userInput = " + userInput);
			getHorseTrackService().processHorseTrackUserInput(userInput);
			userInput = br.readLine().trim().toLowerCase();
		}
	}
	
	private static HorseTrackService getHorseTrackService() {
		if (horseTrackService == null) {
			horseTrackService = new HorseTrackService();
		}
		return horseTrackService;
	}
}
