package com.pluralsight.model;

public class Drink {
    private String size;    // "Small", "Medium", "Large"
    private String flavor;
    private double price;

    // Pricing
    private static final double PRICE_SMALL  = 2.00;
    private static final double PRICE_MEDIUM = 2.50;
    private static final double PRICE_LARGE  = 3.00;

    public Drink(String size, String flavor) {
        this.size   = size;
        this.flavor = flavor;
        this.price  = calculatePrice(size);
    }

    private double calculatePrice(String size) {
        if (size.equalsIgnoreCase("Small")) {
            return PRICE_SMALL;
        } else if (size.equalsIgnoreCase("Medium")) {
            return PRICE_MEDIUM;
        } else { // anything else is Large
            return PRICE_LARGE;
        }
    }

    public String getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s %s drink – $%.2f", size, flavor, price);
    }
}