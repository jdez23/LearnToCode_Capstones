package com.pluralsight.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Sandwich> sandwiches;
    private List<Drink> drinks;
    private List<Chips> chips;
    private LocalDateTime orderTime;

    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks     = new ArrayList<>();
        this.chips      = new ArrayList<>();
        this.orderTime = LocalDateTime.now();
    }


    public void addSandwich(Sandwich s) {
        sandwiches.add(s);
    }
    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public void addDrink(Drink d) {
        drinks.add(d);
    }
    public List<Drink> getDrinks() {
        return drinks;
    }

    public void addChips(Chips c) {
        chips.add(c);
    }
    public List<Chips> getChips() {
        return chips;
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Sandwich s : sandwiches) {
            total += s.getPrice();
        }
        for (Drink d : drinks) {
            total += d.getPrice();
        }
        for (Chips c : chips) {
            total += c.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order placed at ")
                .append(orderTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .append("\n------------------------------\n");

        for (Sandwich s : sandwiches) {
            sb.append(s).append("\n");
        }
        for (Drink d : drinks) {
            sb.append(d).append("\n");
        }
        for (Chips c : chips) {
            sb.append(c).append("\n");
        }

        sb.append("------------------------------\n")
                .append(String.format("Total: $%.2f", getTotalPrice()));

        return sb.toString();
    }


    public String toReceiptString() {
        String timestamp = orderTime.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        StringBuilder sb = new StringBuilder();

        sb.append("Order#: ").append(timestamp).append("\n\n");

        sb.append(this.toString()).append("\n");

        return sb.toString();
    }

    public String getReceiptFileName() {
        return orderTime.format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss")) + ".txt";
    }
}
