package ca.college.lasalle;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Ayrton Amaral - 202234145
 * Bruno Landeiro - 202234156
 * Carolina Ruiz - 202234358
 * */
public class Controller {

	public static List<Product> products = DataProvider.initialProducts();
	
	public static void mainScreen() {
		Menu menu = new Menu();
		
		do {
			//Display the menu options
	        menu.displayMenu();

	        //Read selected option from the console
	        menu.selectOption();

	        switch(menu.getSelectedOption()) {
				case 1:
					//Add a chocolate bar
					break;

				case 2:
					// Add a sandwich
					break;

				case 3:
					addGas();
					break;

				case 4:
					// Add coffee
					break;

				case 5:
					displayAllChocolateBars();
					break;

				case 6:
					// Compare two chocolate bars and display which one is healthier
					System.out.println("\nAvailable Chocolate Bars:");
					for(Product product : products)	{
						if(product instanceof ChocolateBar){
							System.out.println(product);
						}
					}
					System.out.println("\nWhich sandwiches would you like to compare?");
					ChocolateBar chocolate1 = findChocolate("Enter the first chocolate id: ");
					ChocolateBar chocolate2 = findChocolate("Enter the second chocolate id: ");
					compareTwoChocolates(chocolate1, chocolate2);
					break;

				case 7:
					//Display all sandwiches
					break;

				case 8:
					// Compare two sandwiches and display which one is cheaper
					System.out.println("Available Sandwiches:");
					for(Product product : products) {
						if(product instanceof Sandwich){
							System.out.println(product.toString());
						}
					}
					System.out.println("Which sandwiches would you like to compare?");
					Sandwich sandwichOne = readSandwichFromConsole("Enter the first sandwich id: ");
					Sandwich sandwichTwo = readSandwichFromConsole("Enter the second sandwich id: ");
					compareTwoSandwiches(sandwichOne, sandwichTwo);
					break;

				case 9:
					//Sell an edible item
					break;

				case 10:
					//Sell gas
					break;

				case 11:
					// Sell coffee
					break;

				case 12:
					displayGasInTanks();

					break;
			}//end of switch case
	        
		}while(menu.getSelectedOption()!=13);
	}//end of mainScreen method

	private static void addGas() {
		Scanner scanner = new Scanner(System.in);
		double amountOfGas = 0;
		do {
			System.out.println("Enter gas amount to add: ");
			try{
				amountOfGas = scanner.nextDouble();
				if(amountOfGas <= 0){
					System.out.println("Gas amount should be a number greater than 0.");
				}
			}
			catch (Exception e){
				System.out.println("Gas amount should be a numerical value.");
				scanner.next();
			}
		} while(amountOfGas <= 0);

		// Casting the product of type Gas and adding
		((Gas) products.get(0)).addGas(amountOfGas);
		System.out.println("The gas amount was added successfully.");
	}

	private static Sandwich readSandwichFromConsole(String displayMessage) {
		Sandwich sandwich = null;
		do {
			int id = readIntegerFromConsole(displayMessage);
			try {
				sandwich = findSandwichById(id);
			}catch (Exception exception) {
				System.out.println(exception.getMessage());
			}
		} while (sandwich == null);
		return sandwich;
	}

	private static int readIntegerFromConsole(String displayMessage)
	{
		Scanner scanner = new Scanner(System.in);
		Integer i = null;
		do {
			System.out.println(displayMessage);
			try {
				i = scanner.nextInt();
			} catch (Exception exception){
				System.out.println("The input should be an integer number.");
				scanner.next();
			}
		} while (i == null);
		return i;
	}

	private static void displayAllChocolateBars() {
		System.out.println("Chocolate Bars:");
		//Using polymorphism to sisplay all Chocolate Bars
		for(Product product : products) {
			if(product instanceof ChocolateBar){
				System.out.println(product.toString());
			}
		}
	}

	private static void compareTwoSandwiches(Sandwich sandwichOne, Sandwich sandwichTwo) {
		int result = sandwichOne.compareTo(sandwichTwo);
		if(result == 0) {
			System.out.println("Both sandwichs have the same price");
		}
		else if(result < 0) {
			System.out.println(String.format("%s \n is cheaper than \n%s", sandwichOne, sandwichTwo));
		}
		else if(result > 0) {
			System.out.println(String.format("%s \n is cheaper than \n%s", sandwichTwo, sandwichOne));
		}
	}

	private static Sandwich findSandwichById(int id) throws RuntimeException
	{
		for(Product product : products)
		{
			if(product instanceof Sandwich && product.getId().equals(id))
			{
				return (Sandwich) product;
			}
		}
		throw new RuntimeException("Sandwich not found.");
	}

	private static void displayGasInTanks() {
		double amountInTheTanks = ((Gas) products.get(0)).getAmountInTheTanks();
		System.out.println("The total amount of gas in the tanks is: " + amountInTheTanks + " liters.");
	}

	private static void compareTwoChocolates(ChocolateBar chocolate1, ChocolateBar chocolate2) {
		int result = chocolate1.getNumberOfCalories().compareTo(chocolate2.getNumberOfCalories());

		if(result == 0) {
			System.out.println("Both chocolates have the same number of calories.");
		}
		else if(result < 0) {
			System.out.println(String.format("%s \n is healthier than \n%s", chocolate1, chocolate2));
		}
		else if(result > 0) {
			System.out.println(String.format("%s \n is less healthier than \n%s", chocolate1, chocolate2));
		}
	}

	private static ChocolateBar findChocolate (String displayMessage) {
		ChocolateBar chocolate = null;
		do {
			int id = readIntegerFromConsole(displayMessage);
			for(Product product : products) {
				if(id == product.getId() && product instanceof ChocolateBar) {
					chocolate = ((ChocolateBar) product);
				}
			}
			if(chocolate == null) {
				System.out.println("Chocolate id was not found.");
			}
		} while (chocolate == null);
		return chocolate;
	}

}
