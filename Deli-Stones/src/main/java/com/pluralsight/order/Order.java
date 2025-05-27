package com.pluralsight.order;

import com.pluralsight.menu.Chips;
import com.pluralsight.menu.Drink;
import com.pluralsight.model.Sandwich;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Sandwich> sandwiches = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<Chips> chips = new ArrayList<>();
    private String timestamp;

    public void addSandwich(Sandwich s) { sandwiches.add(s); }
    public void addDrink(Drink d)     { drinks.add(d); }
    public void addChips(Chips c)     { chips.add(c); }

    public double getTotal() {
        double total = 0;
        total += sandwiches.stream().mapToDouble(Sandwich::getPrice).sum();
        total += drinks.stream().mapToDouble(Drink::getPrice).sum();
        total += chips.stream().mapToDouble(Chips::getPrice).sum();
        return total;
    }
}