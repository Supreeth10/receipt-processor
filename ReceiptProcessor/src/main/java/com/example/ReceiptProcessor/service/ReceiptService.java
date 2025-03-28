package com.example.ReceiptProcessor.service;

import com.example.ReceiptProcessor.model.Receipt;
import com.example.ReceiptProcessor.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public String processReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receiptRepository.save(id, receipt);
        return id;
    }

    public int calculatePoints(Receipt receipt) {
        int points = 0;

        // Rule 1: One point per alphanumeric character in retailer name
        points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();


        // Rule 2: 50 points for a round dollar amount
        double total = Double.parseDouble(receipt.getTotal());
        if (total % 1 == 0) points += 50;


        // Rule 3: 25 points if the total is a multiple of 0.25
        if (total % 0.25 == 0) points += 25;


        // Rule 4: 5 points for every two items
        points += (receipt.getItems().size() / 2) * 5;


        for (var item : receipt.getItems()) {
            String desc = item.getShortDescription().trim();
            if (desc.length() % 3 == 0) {
                double price = Double.parseDouble(item.getPrice());  // Parse price once
                points += (int) Math.ceil(price * 0.2);  // Cast explicitly to int for clarity
            }
        }


        // Rule 6: 6 points if the purchase date is an odd day
        int day = Integer.parseInt(receipt.getPurchaseDate().split("-")[2]);
        if (day % 2 != 0) points += 6;


        // Rule 7: 10 points if the purchase time is between 2:00 PM and 4:00 PM
        String[] timeParts = receipt.getPurchaseTime().split(":");
        int hour = Integer.parseInt(timeParts[0]);
        if (hour >= 14 && hour < 16) {
            points += 10;
        }


        return points;
    }

    public Integer getPoints(String id) {
        Receipt receipt = receiptRepository.findById(id);
        return (receipt != null) ? calculatePoints(receipt) : null;
    }

}
