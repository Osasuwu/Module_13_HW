package com.homework.ticketmachine.statemachine;

import com.homework.ticketmachine.models.TicketType;

import java.math.BigDecimal;

/**
 * State: Ticket dispensed
 */
public class TicketDispensedState implements ITicketMachineState {
    @Override
    public void selectTicket(TicketMachine context, TicketType ticket) {
        context.reset();
        context.selectTicket(ticket);
    }

    @Override
    public void insertMoney(TicketMachine context, BigDecimal amount) {
        throw new IllegalStateException("Билет уже выдан");
    }

    @Override
    public void dispenseTicket(TicketMachine context) {
        throw new IllegalStateException("Билет уже выдан");
    }

    @Override
    public void cancel(TicketMachine context) {
        context.reset();
    }
}
