package com.homework.ticketmachine.statemachine;

import com.homework.ticketmachine.models.TicketType;

import java.math.BigDecimal;

/**
 * State: Idle - waiting for ticket selection
 */
public class IdleState implements ITicketMachineState {
    @Override
    public void selectTicket(TicketMachine context, TicketType ticket) {
        if (!context.getInventory().isAvailable(ticket)) {
            throw new IllegalStateException("Билет недоступен");
        }
        context.setSelectedTicket(ticket);
        context.setState(new WaitingForMoneyState());
    }

    @Override
    public void insertMoney(TicketMachine context, BigDecimal amount) {
        throw new IllegalStateException("Сначала выберите билет");
    }

    @Override
    public void dispenseTicket(TicketMachine context) {
        throw new IllegalStateException("Сначала выберите билет");
    }

    @Override
    public void cancel(TicketMachine context) {
        System.out.println("Нечего отменять");
    }
}
