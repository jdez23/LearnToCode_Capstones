package com.pluralsight.model;

import java.util.List;

public class Sandwich {
    private int size;
    private String bread;
    private String meat;
    private String cheese;
    private boolean extraMeat;
    private boolean extraCheese;
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

    private static final double EXTRA_CHEESE_4 = .30;
    private static final double EXTRA_CHEESE_8 = .60;
    private static final double EXTRA_CHEESE_12 = .90;

    private static final double EXTRA_MEAT_4 = .50;
    private static final double EXTRA_MEAT_8 = 1.00;
    private static final double EXTRA_MEAT_12 = 1.50;

    public Sandwich(int size,
                    String bread,
                    String meat,
                    String cheese,
                    boolean extraMeat,
                    boolean extraCheese,
                    List<String> sauces,
                    boolean toasted) {
        this.size = size;
        this.bread = bread;
        this.meat = meat;
        this.cheese = cheese;
        this.extraMeat = extraMeat;
        this.extraCheese = extraCheese;
        this.sauces = sauces;
        this.toasted = toasted;
    }

    public int getSize() {
        return size;
    }

    public String getBread() {
        return bread;
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

        double premiumCharge = 0.0;
        double meatCharge = 0.0;
        double cheeseCharge = 0.0;

        if (!meat.isEmpty() || !cheese.isEmpty()) {
            premiumCharge = switch (size) {
                case 4 -> PREMIUM_TOPPING_COST_4;
                case 8 -> PREMIUM_TOPPING_COST_8;
                case 12 -> PREMIUM_TOPPING_COST_12;
                default -> 0.0;
            };
        }

        if (!meat.isEmpty() && extraMeat) {
            meatCharge = switch (size) {
                case 4 -> EXTRA_MEAT_4;
                case 8 -> EXTRA_MEAT_8;
                case 12 -> EXTRA_MEAT_12;
                default -> 0.0;
            };
        }

        if (!cheese.isEmpty() && extraCheese) {
            cheeseCharge = switch (size) {
                case 4 -> EXTRA_CHEESE_4;
                case 8 -> EXTRA_CHEESE_8;
                case 12 -> EXTRA_CHEESE_12;
                default -> 0.0;
            };
        }

        return base + premiumCharge + meatCharge + cheeseCharge;
    }

    @Override
    public String toString() {
        double base = switch (size) {
            case 4 -> PRICE_4_INCH;
            case 8 -> PRICE_8_INCH;
            case 12 -> PRICE_12_INCH;
            default -> 0.00;
        };

        double premiumCharge = (!meat.isEmpty() || !cheese.isEmpty()) ? switch (size) {
            case 4 -> PREMIUM_TOPPING_COST_4;
            case 8 -> PREMIUM_TOPPING_COST_8;
            case 12 -> PREMIUM_TOPPING_COST_12;
            default -> 0.0;
        } : 0.0;

        double meatCharge = (!meat.isEmpty() && extraMeat) ? switch (size) {
            case 4 -> EXTRA_MEAT_4;
            case 8 -> EXTRA_MEAT_8;
            case 12 -> EXTRA_MEAT_12;
            default -> 0.0;
        } : 0.0;

        double cheeseCharge = (!cheese.isEmpty() && extraCheese) ? switch (size) {
            case 4 -> EXTRA_CHEESE_4;
            case 8 -> EXTRA_CHEESE_8;
            case 12 -> EXTRA_CHEESE_12;
            default -> 0.0;
        } : 0.0;

        double total = base + premiumCharge + meatCharge + cheeseCharge;

        return String.format(
                "%d\" %s%s sandwich\n" +
                        "- Meat: %s%s\n" +
                        "- Cheese: %s%s\n" +
                        "- Sauces: %s\n" +
                        "- Base Price: $%.2f\n" +
                        "- Premium Topping Charge: $%.2f\n" +
                        "- Extra Meat Charge: $%.2f\n" +
                        "- Extra Cheese Charge: $%.2f\n" +
                        "=> Total: $%.2f",
                size,
                bread,
                toasted ? " toasted" : "",
                meat, extraMeat ? " (extra)" : "",
                cheese, extraCheese ? " (extra)" : "",
                sauces,
                base,
                premiumCharge,
                meatCharge,
                cheeseCharge,
                total
        );
    }

}
