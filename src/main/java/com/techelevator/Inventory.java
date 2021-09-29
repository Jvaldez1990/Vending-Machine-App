package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    final File C_S_V;
    private List<Snack> allSnacks = new ArrayList<>();

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
                    allSnacks.add(a);
                }else if (everythingSplitUp[3].contentEquals("Candy")) {
                    Snack a = new Candy (everythingSplitUp[0], everythingSplitUp[1], Double.parseDouble(everythingSplitUp[2]));
                    allSnacks.add(a);
                }else if (everythingSplitUp[3].contentEquals("Drink")) {
                    Snack a = new Drink (everythingSplitUp[0], everythingSplitUp[1], Double.parseDouble(everythingSplitUp[2]));
                    allSnacks.add(a);
                }else if (everythingSplitUp[3].contentEquals("Gum")) {
                    Snack a = new Gum (everythingSplitUp[0], everythingSplitUp[1], Double.parseDouble(everythingSplitUp[2]));
                    allSnacks.add(a);
                }else {
                    System.out.println(everythingSplitUp[3]);
                } // ends if/else
            } // ends while
        } // ends try
    } // ends stockInventory

    public List<Snack> interactWithInventory() {
        return allSnacks;
    }

    public void displayInventoryInConsole() {
        for (Snack snack : allSnacks) {
            System.out.print(snack.getLocation()+ " ");
            System.out.print(snack.getName() + " ");
            System.out.print(snack.getPrice() + " ");
            System.out.print(snack.getRemainingQuantity() + "\n");
        }
    }
}