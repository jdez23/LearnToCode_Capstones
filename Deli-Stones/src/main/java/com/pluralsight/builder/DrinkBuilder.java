package com.pluralsight.builder;

import com.pluralsight.model.Drink;

import java.util.Scanner;

public class DrinkBuilder {
    public static Drink build(Scanner scanner) {
        System.out.println("Select drink size (S)mall, (M)edium, (L)arge:");
        String sizeInput = scanner.nextLine().trim();
        String size = switch (sizeInput.toUpperCase()) {
            case "S" -> "Small";
            case "M" -> "Medium";
            default -> "Large";
        };

        System.out.println("What will be the drink flavor?");
        String flavor = scanner.nextLine().trim();

        return new Drink(size, flavor);
    }
}
