package com.finanbot.core.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Investment {
    private final UUID id;
    private final UUID userId;
    private String name;
    private String type; // e.g., "STOCK", "FII", "CRYPTO", "FIXED_INCOME"
    private BigDecimal quantity;
    private BigDecimal purchasePrice;
    private BigDecimal currentPrice;
    private LocalDateTime purchaseDate;

    public Investment(UUID userId, String name, String type, BigDecimal quantity, BigDecimal purchasePrice, LocalDateTime purchaseDate) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.currentPrice = purchasePrice; // Initially, current price is purchase price
        this.purchaseDate = purchaseDate;
        validate();
    }

    public Investment(UUID id, UUID userId, String name, String type, BigDecimal quantity, BigDecimal purchasePrice, BigDecimal currentPrice, LocalDateTime purchaseDate) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
        this.purchaseDate = purchaseDate;
    }

    private void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Investment name cannot be empty.");
        }
        if (quantity == null || quantity.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        if (purchasePrice == null || purchasePrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Purchase price cannot be negative.");
        }
    }

    public void updateCurrentPrice(BigDecimal newPrice) {
        if (newPrice == null || newPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Current price cannot be negative.");
        }
        this.currentPrice = newPrice;
    }

    public BigDecimal calculateTotalValue() {
        return this.quantity.multiply(this.currentPrice);
    }

    public BigDecimal calculateProfitability() {
        if (purchasePrice.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO; // Prevent division by zero if purchase price was 0 (e.g., gift)
        }
        BigDecimal profit = currentPrice.subtract(purchasePrice);
        return profit.divide(purchasePrice, 4, java.math.RoundingMode.HALF_UP).multiply(new BigDecimal("100"));
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public String getName() { return name; }
    public String getType() { return type; }
    public BigDecimal getQuantity() { return quantity; }
    public BigDecimal getPurchasePrice() { return purchasePrice; }
    public BigDecimal getCurrentPrice() { return currentPrice; }
    public LocalDateTime getPurchaseDate() { return purchaseDate; }
}