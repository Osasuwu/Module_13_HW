package com.homework.ticketmachine.services;

import com.homework.ticketmachine.models.TicketType;

import java.util.*;

/**
 * Service for managing ticket catalog
 */
public class TicketCatalogService {
    private final Map<String, TicketType> catalog = new HashMap<>();

    /**
     * Seeds the catalog with ticket types
     */
    public void seed(TicketType... tickets) {
        for (TicketType ticket : tickets) {
            catalog.put(ticket.getCode(), ticket);
        }
    }

    /**
     * Finds a ticket by code
     */
    public TicketType find(String code) {
        return catalog.get(code);
    }

    /**
     * Returns all tickets
     */
    public Collection<TicketType> all() {
        return catalog.values();
    }
}
