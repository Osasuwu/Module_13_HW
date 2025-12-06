package com.homework.ticketmachine.services;

import java.math.BigDecimal;

/**
 * Service for managing payment operations
 */
public class PaymentService {
    private BigDecimal currentAmount = BigDecimal.ZERO;

    /**
     * Adds money to the current transaction
     */
    public void addMoney(BigDecimal amount) {
        currentAmount = currentAmount.add(amount);
        System.out.println(String.format("Внесено: %.2f₽. Всего: %.2f₽", amount, currentAmount));
    }

    /**
     * Gets the current amount
     */
    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    /**
     * Checks if there's enough money for the given price
     */
    public boolean hasEnoughMoney(BigDecimal price) {
        return currentAmount.compareTo(price) >= 0;
    }

    /**
     * Completes the transaction and returns change
     */
    public BigDecimal completeTransaction(BigDecimal price) {
        if (!hasEnoughMoney(price)) {
            throw new IllegalStateException("Недостаточно средств");
        }
        BigDecimal change = currentAmount.subtract(price);
        currentAmount = BigDecimal.ZERO;
        return change;
    }

    /**
     * Cancels the transaction and returns all money
     */
    public BigDecimal cancelTransaction() {
        BigDecimal refund = currentAmount;
        currentAmount = BigDecimal.ZERO;
        return refund;
    }
}
