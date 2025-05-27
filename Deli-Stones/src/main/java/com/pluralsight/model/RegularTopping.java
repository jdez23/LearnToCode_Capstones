package com.pluralsight.model;

public class RegularTopping extends Topping {
    public RegularTopping(String name) { super(name); }
    @Override public double getPrice() { return 0.50; }
}