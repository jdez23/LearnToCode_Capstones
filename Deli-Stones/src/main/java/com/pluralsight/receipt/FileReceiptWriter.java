package com.pluralsight.receipt;

import com.pluralsight.model.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileReceiptWriter implements ReceiptWriter {
    @Override
    public void writeReceipt(Order order) {
        try {
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            File dir = new File("receipts");
            if (!dir.exists()) dir.mkdir();

            File file = new File(dir, timestamp + ".txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(order.toReceiptString());
            bw.close();
        } catch (Exception e) {
            System.out.println("Error writing receipt: " + e.getMessage());
        }
    }
}