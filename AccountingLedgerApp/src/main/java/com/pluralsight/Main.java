package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Ledger ledger = new Ledger();

    public static void main(String[] args) {
        ledger.loadTransactionsFromFile("transactions.csv");

        boolean running = true;

        while (running) {
            showHomeScreen();
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D":
                    addDepositScreen();
                    break;
                case "P":
                    makePaymentScreen();
                    break;
                case "L":
                    showLedgerScreen();
                    break;
                case "X":
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again!");
                    break;
            }
        }
    }

    public static void showHomeScreen() {
        System.out.println("\nWelcome in! This is a Ledger App!");
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

        System.out.println("\n Enter amount (00.00): ");
        String amountInput = scanner.nextLine();
        double amount = Double.parseDouble(amountInput);

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

        System.out.print("Enter amount (00.00): ");
        double amount = Double.parseDouble(scanner.nextLine());
        amount = -Math.abs(amount);

        Transaction payment = new Transaction(LocalDateTime.now(), description, vendor, amount);
        ledger.addTransaction(payment);
        ledger.saveTransactionsToFile("transactions.csv");

        System.out.println("Saved to transactions.csv!");
        System.out.println("Payment added successfully!");
    }

    public static void showLedgerScreen() {
        while (true) {
            System.out.println("\n--- Ledger Menu ---");
            System.out.println("A) All Transactions");
            System.out.println("D) Deposits Only");
            System.out.println("P) Payments Only");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("> ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    displayTransactions(ledger.getTransactions());
                    break;
                case "D":
                    displayTransactions(ledger.filterDeposits());
                    break;
                case "P":
                    displayTransactions(ledger.filterPayments());
                    break;
                case "R":
                    showReportsScreen();
                    break;
                case "H":
                    return; // go back to home
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void displayTransactions(ArrayList<Transaction> transactions) {
        // Sort newest first
        transactions.sort((a, b) -> b.getDateTime().compareTo(a.getDateTime()));

        System.out.printf("\n%-12s %-10s %-20s %-20s %10s\n",
                "Date", "Time", "Description", "Vendor", "Amount");

        for (Transaction t : transactions) {
            System.out.printf("%-12s %-10s %-20s %-20s %10.2f\n",
                    t.getDateTime().toLocalDate(),
                    t.getDateTime().toLocalTime(),
                    t.getDescription(),
                    t.getVendor(),
                    t.getAmount());
        }
    }

    public static void showReportsScreen() {
        while (true) {
            System.out.println("\n--- Reports ---");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Custom Search");
            System.out.println("0) Back");
            System.out.print("> ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    displayTransactions(ledger.filterMonthToDate());
                    break;
                case "2":
                    displayTransactions(ledger.filterPreviousMonth());
                    break;
                case "3":
                    displayTransactions(ledger.filterYearToDate());
                    break;
                case "4":
                    displayTransactions(ledger.filterPreviousYear());
                    break;
                case "5":
                    System.out.print("Enter vendor name to search: ");
                    String vendor = scanner.nextLine();
                    displayTransactions(ledger.filterByVendor(vendor));
                    break;
                case "6":
                    customSearchScreen();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void customSearchScreen() {
        System.out.println("\n--- Custom Search ---");

        System.out.print("Start date (yyyy-MM-dd) or leave blank: ");
        String startDateInput = scanner.nextLine();

        System.out.print("End date (yyyy-MM-dd) or leave blank: ");
        String endDateInput = scanner.nextLine();

        System.out.print("Description keyword (optional): ");
        String description = scanner.nextLine().trim();

        System.out.print("Vendor name (optional): ");
        String vendor = scanner.nextLine().trim();

        System.out.print("Amount (exact match, optional): ");
        String amountInput = scanner.nextLine();

        // Convert inputs
        LocalDate startDate = startDateInput.isEmpty() ? null : LocalDate.parse(startDateInput);
        LocalDate endDate = endDateInput.isEmpty() ? null : LocalDate.parse(endDateInput);
        Double amount = amountInput.isEmpty() ? null : Double.parseDouble(amountInput);

        ArrayList<Transaction> results = ledger.customSearch(startDate, endDate, description, vendor, amount);
        displayTransactions(results);
    }


}
