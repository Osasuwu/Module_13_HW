package com.homework.ticketmachine.statemachine;

import com.homework.ticketmachine.models.TicketType;

import java.math.BigDecimal;

/**
 * State: Waiting for money
 */
public class WaitingForMoneyState implements ITicketMachineState {
    @Override
    public void selectTicket(TicketMachine context, TicketType ticket) {
        throw new IllegalStateException("Сначала завершите текущую транзакцию");
    }

    @Override
    public void insertMoney(TicketMachine context, BigDecimal amount) {
        context.getPayment().addMoney(amount);
    }

    @Override
    public void dispenseTicket(TicketMachine context) {
        TicketType ticket = context.getSelectedTicket();
        if (ticket == null) {
            throw new IllegalStateException("Билет не выбран");
        }

        if (!context.getPayment().hasEnoughMoney(ticket.getPrice())) {
            throw new IllegalStateException("Недостаточно средств");
        }

        context.setState(new MoneyReceivedState());
        context.dispenseTicket();
    }

    @Override
    public void cancel(TicketMachine context) {
        context.setState(new TransactionCanceledState());
        context.cancel();
    }
}
