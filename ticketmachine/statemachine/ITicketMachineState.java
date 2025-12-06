package com.homework.ticketmachine.statemachine;

import com.homework.ticketmachine.models.TicketType;

import java.math.BigDecimal;

/**
 * Base interface for ticket machine states
 */
public interface ITicketMachineState {
    void selectTicket(TicketMachine context, TicketType ticket);
    void insertMoney(TicketMachine context, BigDecimal amount);
    void dispenseTicket(TicketMachine context);
    void cancel(TicketMachine context);
}
