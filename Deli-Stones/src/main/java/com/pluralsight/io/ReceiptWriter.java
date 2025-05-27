package com.pluralsight.io;

import com.pluralsight.order.Order;

public interface ReceiptWriter {
    void writeReceipt(Order order);
}