package com.pluralsight.model;

import java.util.ArrayList;
import java.util.List;

public class Sandwich {
    private String size;
    private String breadType;
    private boolean toasted;
    private List<Topping> toppings = new ArrayList<>();

    public Sandwich(String size, String breadType, boolean toasted) {
        this.size = size;
        this.breadType = breadType;
        this.toasted = toasted;
    }

    public void addTopping(Topping t) {
        toppings.add(t);
    }

    public double getPrice() {
        double base = 0;
        switch (size) {
            case "4\"": base = 5.00; break;
            case "8\"": base = 8.00; break;
            case "12\"": base = 11.00; break;
        }
        double extra = toppings.stream().mapToDouble(Topping::getPrice).sum();
        return base + extra;
    }
}