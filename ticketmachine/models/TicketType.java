package com.homework.ticketmachine.models;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a ticket type with code, name and price
 */
public class TicketType {
    private final String code;
    private final String name;
    private final BigDecimal price;

    public TicketType(String code, String name, BigDecimal price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketType that = (TicketType) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return String.format("%s: %s (%.2f₽)", code, name, price);
    }
}
