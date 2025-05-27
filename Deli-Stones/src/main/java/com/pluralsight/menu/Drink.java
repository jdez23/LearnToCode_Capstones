package com.pluralsight.menu;

public class Drink {
    private String flavor;
    private String size;

    public Drink(String flavor, String size) {
        this.flavor = flavor; this.size = size;
    }

    public double getPrice() {
        switch (size) {
            case "small": return 1.50;
            case "medium": return 2.00;
            case "large": return 2.50;
            default: return 0;
        }
    }
}