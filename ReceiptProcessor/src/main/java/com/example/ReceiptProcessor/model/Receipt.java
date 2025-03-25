package com.example.ReceiptProcessor.model;

import lombok.Data;

import java.util.List;

@Data
public class Receipt {
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private List<Item> items;
    private String total;
}
