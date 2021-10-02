package com.techelevator;

import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import static org.junit.Assert.assertEquals;

public class InventoryTest {

     File csv = new File("vendingmachine.csv");
     Inventory inventoryStock = new Inventory(csv);

    @Test
    public void InventoryTests() throws FileNotFoundException {
        inventoryStock.stockInventory();
        inventoryStock.addMoney(5);
        assertEquals("Inventory should have $5", inventoryStock.getMoney(), 5, 0);
        inventoryStock.subMoney(5);
        assertEquals("Inventory should now have $0", inventoryStock.getMoney(), 0, 0);
        inventoryStock.addMoney(5);
        inventoryStock.interactWithInventory("a1");
        assertEquals("Change should be 1.95", 1.95, inventoryStock.getMoney(), 0.001);
        Map<String, Snack>  snackMap = inventoryStock.getSnackMap();
        assertEquals("Not correct item in A1", snackMap.get("A1").getName(), "Potato Crisps" );
        String expected = "Change returned is 7 quarters, 2 dimes, 0 nickels.";
        Assert.assertEquals(inventoryStock.giveChange(), expected);

    }

}
