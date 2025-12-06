package com.homework.ticketmachine.statemachine;

import com.homework.ticketmachine.models.TicketType;
import com.homework.ticketmachine.services.InventoryService;
import com.homework.ticketmachine.services.PaymentService;

import java.math.BigDecimal;

/**
 * Ticket vending machine using State pattern
 */
public class TicketMachine {
    private ITicketMachineState state;
    private final PaymentService payment;
    private final InventoryService inventory;
    private TicketType selectedTicket;

    public TicketMachine(PaymentService payment, InventoryService inventory) {
        this.payment = payment;
        this.inventory = inventory;
        this.state = new IdleState();
    }

    public void setState(ITicketMachineState state) {
        this.state = state;
    }

    public PaymentService getPayment() {
        return payment;
    }

    public InventoryService getInventory() {
        return inventory;
    }

    public TicketType getSelectedTicket() {
        return selectedTicket;
    }

    public void setSelectedTicket(TicketType ticket) {
        this.selectedTicket = ticket;
    }

    public void reset() {
        this.selectedTicket = null;
        this.state = new IdleState();
    }

    // Public API methods delegate to current state
    public void selectTicket(TicketType ticket) {
        state.selectTicket(this, ticket);
    }

    public void insertMoney(BigDecimal amount) {
        state.insertMoney(this, amount);
    }

    public void dispenseTicket() {
        state.dispenseTicket(this);
    }

    public void cancel() {
        state.cancel(this);
    }
}
