package com.example.ReceiptProcessor.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    @NotBlank(message = "Short description is required.")
    private String shortDescription;

    @NotBlank(message = "Price is required.")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Price must be a valid decimal amount (e.g., 2.25).")
    private String price;
}
