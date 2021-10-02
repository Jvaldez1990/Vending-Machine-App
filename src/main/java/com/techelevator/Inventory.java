package com.techelevator;

import com.techelevator.util.VMLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Inventory {
    final File C_S_V;
    private Map<String,Snack> snackMap = new HashMap<>();
    private List<Snack> snackList = new ArrayList<>();
    private double money;

    public Inventory(File C_S_V) {
        this.C_S_V = C_S_V;
        try {
            this.stockInventory();
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found");
        }
    }
    public void stockInventory() throws FileNotFoundException{
        try(Scanner fileScanner = new Scanner(C_S_V)) {
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String [] everythingSplitUp = line.split("\\|");
                if (everythingSplitUp[3].contentEquals("Chip")) {
                    Snack a = new Chip (everythingSplitUp[0], everythingSplitUp[1], Double.parseDouble(everythingSplitUp[2]));
                    snackMap.put(everythingSplitUp[0], a);
                    snackList.add(a);
                }else if (everythingSplitUp[3].contentEquals("Candy")) {
                    Snack a = new Candy (everythingSplitUp[0], everythingSplitUp[1], Double.parseDouble(everythingSplitUp[2]));
                    snackMap.put(everythingSplitUp[0], a);
                    snackList.add(a);
                }else if (everythingSplitUp[3].contentEquals("Drink")) {
                    Snack a = new Drink (everythingSplitUp[0], everythingSplitUp[1], Double.parseDouble(everythingSplitUp[2]));
                    snackMap.put(everythingSplitUp[0], a);
                    snackList.add(a);
                }else if (everythingSplitUp[3].contentEquals("Gum")) {
                    Snack a = new Gum (everythingSplitUp[0], everythingSplitUp[1], Double.parseDouble(everythingSplitUp[2]));
                    snackMap.put(everythingSplitUp[0], a);
                    snackList.add(a);
                }else {
                    System.out.println(everythingSplitUp[3]);
                }
            }
        }
    }

    public Map<String,Snack> interactWithInventory(String code) {
            if (!snackMap.containsKey(code.toUpperCase())){
                System.out.println();
                System.out.println("Invalid item code");
                System.out.println();
            }
            else if (snackMap.get(code.toUpperCase()).getRemainingQuantity() < 1) {
                System.out.println();
                System.out.println("SOLD OUT");
                System.out.println();
            }
            else if (money < snackMap.get(code.toUpperCase()).getPrice()) {
                System.out.println();
                System.out.println("Not enough money");
                System.out.println();
            }
            else if (money >= snackMap.get(code.toUpperCase()).getPrice()) {
                subMoney(snackMap.get(code.toUpperCase()).getPrice());
                System.out.println();
                System.out.printf(snackMap.get(code.toUpperCase()).getName() + " " + snackMap.get(code.toUpperCase()).getPrice()
                + " $%.2f", money);
                System.out.println();
                System.out.println(snackMap.get(code.toUpperCase()).printout()); // unique item message
                snackMap.get(code.toUpperCase()).removeOneItem(); // remove 1 object from inventory
                System.out.println();
                VMLog.SaleLog(snackMap.get(code.toUpperCase()).getName(), code.toUpperCase() , money, money-snackMap.get(code.toUpperCase()).getPrice());
            }
        return snackMap;
    }

    public void addMoney (double cash) {
        money += cash;
        VMLog.feedLog(cash, money);
    }
    public double getMoney () {
        return money;
    }
    public void subMoney (double cost) {
        money -= cost;
    }
    public Boolean hasKey (String code){
        return snackMap.containsKey(code);
    }

    public void giveChange() {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

        while (money > 0.01) {
            if (money >= 0.25) {
                quarters++;
                money -= 0.25;
            } else if (money > 0.05) {
                dimes++;
                money -= 0.10;
            } else if (money == 0.05) {
                nickels++;
                money -= 0.05;
            }
        }
        System.out.println();
        System.out.println("Change returned is " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels.");
        System.exit(1);
    }

    public void displayInventoryInConsole() {
        for (Snack snack: snackList) {
            System.out.print(snack.getLocation()+ " ");
            System.out.print(snack.getName() + " ");
            System.out.print(snack.getPrice() + " ");
            System.out.print(snack.getRemainingQuantity() + "\n");
        }
    }
}