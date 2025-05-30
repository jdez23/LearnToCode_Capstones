package com.pluralsight.model;

public class Chips {
    private double price;

    private static final double STANDARD_PRICE = 1.50;

    public Chips() {
        this.price = STANDARD_PRICE;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Chips – $%.2f", price);
    }
}