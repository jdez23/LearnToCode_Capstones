package com.pluralsight.builder;

import com.pluralsight.model.Sandwich;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SandwichBuilder {
    public static Scanner scanner = new Scanner(System.in);

    public static Sandwich build() {
        System.out.println("\n--- Build a Sandwich ---");

        System.out.print("Select size (4, 8, or 12 inches): ");
        int size = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Select bread (white, wheat, rye, wrap): ");
        String bread = scanner.nextLine().trim();

        System.out.println("Would you like premium toppings? (Y/N): ");
        boolean premiumToppings = scanner.nextLine().trim().equalsIgnoreCase("Y");

        List<String> sauces = new ArrayList<>();
        System.out.println("Enter sauces (blank to finish):");
        while (true) {
            System.out.print("  Sauce: ");
            String s = scanner.nextLine().trim();
            if (s.isEmpty()) break;
            sauces.add(s);
        }

        System.out.print("Toast sandwich? (Y/N): ");
        boolean toasted = scanner.nextLine().trim().equalsIgnoreCase("Y");

        return new Sandwich(size, bread, premiumToppings, sauces, toasted);
    }
}
