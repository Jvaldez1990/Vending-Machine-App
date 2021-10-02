package com.techelevator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SnackTest {
    @Test
    public void SnackTest() {
        Snack snack = new Snack("A1", "Cheetos", 1.50) {
            @Override
            public String printout() {
                return null;
            }};
        Snack gum = new Gum("A2", "Big Chew", 1.00);

        assertEquals("Price is not correct", 1.50, snack.getPrice(), 0.0);
        assertEquals("Snacks should start with 5 in inventory", snack.getRemainingQuantity(), 5);
        assertEquals("Count should be 4 after 1 is removed", snack.removeOneItem(), 4);
        assertEquals("Cheetos", snack.getName());
        assertEquals("A1", snack.getLocation());
        assertEquals("Chew Chew, Yum!", gum.printout());

    }
}