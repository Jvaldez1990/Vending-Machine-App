package com.techelevator;

public class Candy extends Snack{

    public Candy(String location, String name, double price) {
        super(location, name, price);

    }

    @Override
    public String printout() {
        return "Munch Munch, Yum!";
    }


}
