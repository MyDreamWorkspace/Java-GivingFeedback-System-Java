package com.lawrence;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class WrestleManiaImplementation {

	/**
	 * Please Write the Test Cases for WrestleManiaImplementation Class. 
	 * 1. Equal Test for Sum of Weight 
	 * 2. True Test for Wrestler Name 
	 * 3. Equal Test for Maximum Weight
	 */
	
	// get sum where weight > 200
	public int getSumOfWeight(List<WrestleEntity> wrestlelist) {
		
		return wrestlelist.stream()
				.filter(wrestle -> wrestle.getWeight() > 200).mapToInt(WrestleEntity::getWeight).sum();
	}

	// returns all Wrestler Names
	public List<String> getWrestlerName(List<WrestleEntity> wrestlelist) {

		return wrestlelist.stream()
				.map(WrestleEntity::getName).collect(Collectors.toList());
	}

	// returns Maximum Weight of Wrestler
	public int getMaxWeight(List<WrestleEntity> wrestlelist) {

		return wrestlelist.stream()
				.max(Comparator.comparing(WrestleEntity::getWeight)).get().getWeight();
	}
 
	public static void main(String[] args) {

		System.out.println("Enter how many records you want :");

		Scanner scanner = new Scanner(System.in);
		int numberOfRecords = scanner.nextInt();
		
		System.out.println();

		List<WrestleEntity> wrestlerlist = new ArrayList<>();

		for (int i = 0; i < numberOfRecords; i++) {

			System.out.print("Enter the name of wrestler: ");
			String name = scanner.next();

			System.out.print("Enter the weight of wrestler: ");
			int weight = scanner.nextInt();

			WrestleEntity wrestleEntity = new WrestleEntity(name, weight);

			wrestlerlist.add(wrestleEntity);
		}

		System.out.println();
		
		WrestleManiaImplementation wrestleManiaImplementation = new WrestleManiaImplementation();

		System.out.println("Sum of weight above 200: "
				+ wrestleManiaImplementation.getSumOfWeight(wrestlerlist) + "\n");

		System.out.print("Name of all wrestler: " + wrestleManiaImplementation.getWrestlerName(wrestlerlist));

		System.out.print("Highest weight among the wrestlers : " + wrestleManiaImplementation.getMaxWeight(wrestlerlist));

	}

}
