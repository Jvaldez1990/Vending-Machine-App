package com.techelevator;

public class Drink extends Snack {

    public Drink(String location, String name, double price) {
        super(location, name, price);
    }

    public String printout() {
        return "Glug Glug, Yum!";
    }

}
