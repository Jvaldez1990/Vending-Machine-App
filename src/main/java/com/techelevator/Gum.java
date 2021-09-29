package com.techelevator;

public class Gum extends Snack{

    public Gum(String location, String name, double price) {
        super(location, name, price);
    }

    @Override
    public String printout() {
        return "Chew Chew, Yum!";
    }


}
