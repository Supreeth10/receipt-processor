package com.example.ReceiptProcessor.controller;

import com.example.ReceiptProcessor.exception.BadRequestException;
import com.example.ReceiptProcessor.exception.NotFoundException;
import com.example.ReceiptProcessor.model.Receipt;
import com.example.ReceiptProcessor.service.ReceiptService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity<Map<String, String>> processReceipt(@Valid @RequestBody Receipt receipt) {
        String id = receiptService.processReceipt(receipt);
        Map<String, String> response = new HashMap<>();
        response.put("id", id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<Map<String, Integer>> getPoints(@PathVariable String id) {
        Integer points = receiptService.getPoints(id);
        if (points == null) {
            throw new NotFoundException("No receipt found for ID: " + id);
        }
        Map<String, Integer> response = new HashMap<>();
        response.put("points", points);
        return ResponseEntity.ok(response);
    }
}
