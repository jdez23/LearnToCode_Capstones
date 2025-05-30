package com.pluralsight.ui;

import com.pluralsight.builder.*;
import com.pluralsight.receipt.*;
import com.pluralsight.model.*;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final ReceiptWriter receiptWriter = new FileReceiptWriter();

    public void start() {
        homeLoop:
        while (true) {
            System.out.println("\n=== Deli-Stone ===");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            System.out.print("Choice: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    takeOrder();
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    break homeLoop;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private void takeOrder() {
        Order order = new Order();
        orderLoop:
        while (true) {
            System.out.println("\n--- Current Order ---");
            System.out.println(order.toReceiptString());
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            System.out.print("Choice: ");
            String cmd = scanner.nextLine().trim();
            switch (cmd) {
                case "1":
                    Sandwich s = SandwichBuilder.build();
                    order.addSandwich(s);
                    break;
                case "2":
                    Drink d = DrinkBuilder.build(scanner);
                    order.addDrink(d);
                    break;
                case "3":
                    Chips c = ChipsBuilder.build();
                    order.addChips(c);
                    break;
                case "4":
                    checkout(order);
                    break orderLoop;
                case "0":
                    System.out.println("Order cancelled.");
                    break orderLoop;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void checkout(Order order) {
        System.out.println("\n--- Checkout ---");
        System.out.println(order.toReceiptString());
        System.out.print("Confirm? (Y/N): ");
        if ("Y".equalsIgnoreCase(scanner.nextLine().trim())) {
            receiptWriter.writeReceipt(order);
            System.out.println("Receipt saved. Thank you!");
        } else {
            System.out.println("Checkout aborted.");
        }
    }
}
