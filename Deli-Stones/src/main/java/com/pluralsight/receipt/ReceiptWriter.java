package com.pluralsight.receipt;

import com.pluralsight.model.Order;

public interface ReceiptWriter {
    void writeReceipt(Order order);
}
