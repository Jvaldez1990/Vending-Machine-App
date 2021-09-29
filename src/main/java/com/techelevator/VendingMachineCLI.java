package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class VendingMachineCLI {
	private double money = 0.00;

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT  };
	private static final String PURCHASE_MENU_OPTION_ONE = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_ONE, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION};

	private File csv = new File("vendingmachine.csv");
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	Scanner scan = new Scanner(System.in);

	private Menu menu;
	private Inventory inventoryStock = new Inventory(csv);

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		//running main menu loop
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				//display items in machine
				inventoryStock.displayInventoryInConsole();
				System.out.println();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//display purchase menu loop
				while (true) {
					String PurchaseMenuChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS, money);
					if (PurchaseMenuChoice.equals(PURCHASE_MENU_OPTION_ONE)) {
						//Feed Money loop
						while (true) {
						System.out.println("Insert Bill or press 0 to go back");
						int inputCash = scan.nextInt();
						money += inputCash;
						if (inputCash == 0)
							break;
						}
					} else if (PurchaseMenuChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
						// Select Product

					} else if (PurchaseMenuChoice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
						//return change in coins and exit
						int quarters = 0;
						int dimes = 0;
						int nickels = 0;

						while (money > 0.00) {
							if (money >= 0.25) {
								quarters++;
								money -= 0.25;
							} else if (money > 0.05) {
								dimes++;
								money -= 0.10;
							} else {
								nickels++;
								money -= 0.05;
							}
						}
						System.out.println();
						System.out.println("Change returned is " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels.");
						break;
					}
				}

			} else if (choice.equals(MAIN_MENU_EXIT)) {
				System.exit(1);
			}
		}
	}
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
