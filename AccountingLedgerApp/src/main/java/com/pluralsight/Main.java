package com.pluralsight;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Ledger ledger = new Ledger();

    public static void main(String[] args) {
        ledger.loadTransactionsFromFile("transactions.csv");

        while (true) {
            showHomeScreen();
            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("D")) {
                addDepositScreen();
            } else if (choice.equals("P")) {
                makePaymentScreen();
            } else if (choice.equals("L")) {
                showLedgerScreen();
            } else if (choice.equals("X")) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option. Try again!");
            }
        }
    }

    public static void showHomeScreen() {
        System.out.println("\nSelect one of the following options:");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment (Debit)");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.print("> ");
    }

    public static void addDepositScreen() {
        System.out.println("\n--- Add Deposit ---");
        System.out.println("\n Enter description: ");
        String description = scanner.nextLine();

        System.out.println("\n Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.println("\n Enter amount: ");
        double amount = scanner.nextDouble();

        Transaction deposit = new Transaction(LocalDateTime.now(), description, vendor, amount);
        ledger.addTransaction(deposit);
        ledger.saveTransactionsToFile("transactions.csv");

        System.out.println("Saved to transactions.csv!");
        System.out.println("Deposit added successfully!");
    }

    public static void makePaymentScreen() {
        System.out.println("\n--- Make Payment ---");
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        amount = -Math.abs(amount);

        Transaction payment = new Transaction(LocalDateTime.now(), description, vendor, amount);
        ledger.addTransaction(payment);
        ledger.saveTransactionsToFile("transactions.csv");

        System.out.println("Saved to transactions.csv!");
        System.out.println("Payment added successfully!");
    }


    public static void showLedgerScreen() {
        System.out.println("\n--- Ledger ---");
        // You'll add real functionality here in Phase 5
    }
}
