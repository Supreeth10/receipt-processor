package com.example.ReceiptProcessor.repository;

import com.example.ReceiptProcessor.model.Receipt;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ReceiptRepository {
    private final Map<String, Receipt> receiptStore = new ConcurrentHashMap<>();

    public void save(String id, Receipt receipt) {
        receiptStore.put(id, receipt);
    }

    public Receipt findById(String id) {
        return receiptStore.get(id);
    }
}
