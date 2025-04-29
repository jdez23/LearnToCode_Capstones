package com.pluralsight;

import java.io.*;
import java.time.*;
import java.util.ArrayList;

public class Ledger {
    private ArrayList<Transaction> transactions;

    public Ledger() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public void loadTransactionsFromFile(String filename) {
        transactions.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    LocalDate date = LocalDate.parse(parts[0]);
                    LocalTime time = LocalTime.parse(parts[1]);
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);

                    LocalDateTime dateTime = LocalDateTime.of(date, time);
                    Transaction t = new Transaction(dateTime, description, vendor, amount);
                    transactions.add(t);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No transactions file found yet. Starting fresh.");
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }


    public void saveTransactionsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Transaction t : transactions) {
                String line = String.format(
                        "%s|%s|%s|%s|%.2f",
                        t.getDateTime().toLocalDate(),
                        t.getDateTime().toLocalTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount()
                );
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }


    public ArrayList<Transaction> filterDeposits() {
        // Will implement later in Phase 6
        return null;
    }

    public ArrayList<Transaction> filterPayments() {
        // Will implement later in Phase 6
        return null;
    }

    public ArrayList<Transaction> filterByVendor(String vendor) {
        // Will implement later in Phase 6
        return null;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
