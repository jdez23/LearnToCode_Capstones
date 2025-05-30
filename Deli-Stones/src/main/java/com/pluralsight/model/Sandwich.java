package com.pluralsight.model;

import java.util.List;

public class Sandwich {
    private int size;
    private String bread;
    private boolean premiumToppings;
    private List<String> sauces;
    private boolean toasted;

    // Base prices for each size
    private static final double PRICE_4_INCH  = 5.50;
    private static final double PRICE_8_INCH  = 7.00;
    private static final double PRICE_12_INCH = 8.50;

    // Premium topping price for each size
    private static final double PREMIUM_TOPPING_COST_4 = 1.00;
    private static final double PREMIUM_TOPPING_COST_8 = 2.00;
    private static final double PREMIUM_TOPPING_COST_12 = 3.00;

    public Sandwich(int size,
                    String bread,
                    boolean premiumToppings,
                    List<String> sauces,
                    boolean toasted) {
        this.size = size;
        this.bread = bread;
        this.premiumToppings = premiumToppings;
        this.sauces = sauces;
        this.toasted = toasted;
    }

    public int getSize() {
        return size;
    }

    public String getBread() {
        return bread;
    }

    public boolean getPremiumToppings() {
        return premiumToppings;
    }

    public List<String> getSauces() {
        return sauces;
    }

    public boolean isToasted() {
        return toasted;
    }

    public double getPrice() {
        double base = switch (size) {
            case 4 -> PRICE_4_INCH;
            case 8 -> PRICE_8_INCH;
            case 12 -> PRICE_12_INCH;
            default -> 0.00;
        };

        double surchargePremium = switch (size) {
            case 4  -> PREMIUM_TOPPING_COST_4;
            case 8  -> PREMIUM_TOPPING_COST_8;
            case 12 -> PREMIUM_TOPPING_COST_12;
            default -> 0.0;
        };

        double premiumCharge = premiumToppings
                ? surchargePremium
                : 0.0;

        return base + premiumCharge;
    }

    @Override
    public String toString() {
        return String.format(
                "%d\" %s%s sandwich (premium: %s; sauces: %s)%s",
                size,
                bread,
                toasted ? " toasted" : "",
                premiumToppings,
                sauces,
                toasted ? " [toasted]" : ""
        );
    }
}
