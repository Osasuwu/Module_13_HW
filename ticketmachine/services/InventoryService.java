package com.homework.ticketmachine.services;

import com.homework.ticketmachine.models.TicketType;

import java.util.HashMap;
import java.util.Map;

/**
 * Service for managing ticket inventory
 */
public class InventoryService {
    private final Map<TicketType, Integer> stock = new HashMap<>();

    /**
     * Seeds the inventory with ticket stock
     */
    public void seedStock(Map<TicketType, Integer> items) {
        stock.putAll(items);
    }

    /**
     * Checks if a ticket is available
     */
    public boolean isAvailable(TicketType ticket) {
        return stock.getOrDefault(ticket, 0) > 0;
    }

    /**
     * Dispenses a ticket (reduces stock by 1)
     */
    public void dispense(TicketType ticket) {
        if (!isAvailable(ticket)) {
            throw new IllegalStateException("Билет недоступен");
        }
        stock.put(ticket, stock.get(ticket) - 1);
    }

    /**
     * Gets the current stock level for a ticket
     */
    public int getStock(TicketType ticket) {
        return stock.getOrDefault(ticket, 0);
    }
}
