package com.pluralsight;

import com.pluralsight.io.*;
import com.pluralsight.order.Order;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to DELI-cious!");
        Order order = new Order();

        // After checkout:
        ReceiptWriter writer = new FileReceiptWriter();
        writer.writeReceipt(order);
        System.out.println("Thank you! Your receipt has been saved.");
    }
}