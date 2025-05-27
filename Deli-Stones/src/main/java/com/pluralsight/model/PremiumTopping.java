package com.pluralsight.model;

public class PremiumTopping extends Topping {
    public PremiumTopping(String name) { super(name); }
    @Override public double getPrice() { return 1.00; }
}
