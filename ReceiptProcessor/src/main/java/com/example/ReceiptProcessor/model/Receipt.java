package com.example.ReceiptProcessor.model;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Receipt {
    @NotNull(message = "Retailer is required.")
    private String retailer;

    @NotBlank(message = "Purchase date is required.")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Purchase date must be in YYYY-MM-DD format.")
    private String purchaseDate;

    @NotBlank(message = "Purchase time is required.")
    @Pattern(regexp = "\\d{2}:\\d{2}", message = "Purchase time must be in HH:mm format.")
    private String purchaseTime;

    @NotNull(message = "Items list cannot be null.")
    @Size(min = 1, message = "There must be at least one item in the receipt.")
    private List<@Valid Item> items;

    @NotBlank(message = "Total amount is required.")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Total must be a valid decimal amount (e.g., 9.00).")
    private String total;
}
