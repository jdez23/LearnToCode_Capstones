package com.pluralsight.io;

import com.pluralsight.order.Order;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileReceiptWriter implements ReceiptWriter {
    @Override
    public void writeReceipt(Order order) {
        try {
            String ts = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            File dir = new File("src/main/resources/receipts");
            dir.mkdirs();
            File file = new File(dir, ts + ".txt");
            FileWriter fw = new FileWriter(file);
            fw.write("Order receipt - " + ts + "\n");
            fw.write("Total: $" + order.getTotal() + "\n");
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
