package com.techelevator;

public class Chip extends Snack{

    public Chip(String location, String name, double price) {
        super(location, name, price);
    }

    @Override
    public String printout() {
        return "Crunch Crunch, Yum!";
    }

}
