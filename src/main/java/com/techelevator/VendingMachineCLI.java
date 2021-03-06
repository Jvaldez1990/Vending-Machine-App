package com.techelevator;

import com.techelevator.util.VMLog;
import com.techelevator.view.Menu;
import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_EXIT  };
	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION};

	private File csv = new File("vendingmachine.csv");
	Scanner scan = new Scanner(System.in);

	private Menu menu;
	private Inventory inventoryStock = new Inventory(csv); // create and stock inventory

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		//running main menu loop
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				//display items in machine
				System.out.println();
				inventoryStock.displayInventoryInConsole();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//display purchase menu loop
				while (true) {
					double money = inventoryStock.getMoney();
							String PurchaseMenuChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS, money);
							if (PurchaseMenuChoice.equals(PURCHASE_MENU_FEED_MONEY)) {
								//Feed Money loop
								while (true) {
									System.out.println();
									System.out.println("Insert Cash | Press 0 or enter to go back");
									int inputCash = 0;
									String inputCashString = scan.nextLine();

									if (inputCashString.equalsIgnoreCase("0") || inputCashString.isEmpty())
										break;
									try {
										inputCash = Integer.parseInt(inputCashString);
										if (!(inputCash == 1) && !(inputCash == 2) && !(inputCash == 5) && !(inputCash == 10) && !(inputCash == 20)){
											throw new IllegalArgumentException("Unrealistic dollar value");
										}
										inventoryStock.addMoney(inputCash);
									}
									catch (NumberFormatException e) {
										System.err.println("Not an whole number");
									} catch (IllegalArgumentException e) {
								System.err.println(e);
							}
						}
					} else if (PurchaseMenuChoice.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
						// Select Product loop
						inventoryStock.displayInventoryInConsole();
						while(true) {
							System.out.println();
							System.out.println("Enter item code: ");
							String desiredItemCode = scan.nextLine();

							inventoryStock.interactWithInventory(desiredItemCode);

							if (!inventoryStock.hasKey(desiredItemCode) ||
									money < inventoryStock.interactWithInventory(desiredItemCode).get(desiredItemCode).getPrice() ||
									inventoryStock.interactWithInventory(desiredItemCode).get(desiredItemCode).getRemainingQuantity() == 0) {
								break;
							}
						}
					} else if (PurchaseMenuChoice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
						//return change in coins and exit
						VMLog.ChangeLog(0, money);
						inventoryStock.giveChange();
						System.exit(1);
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
