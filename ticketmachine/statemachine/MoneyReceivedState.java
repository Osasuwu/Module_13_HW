package com.homework.ticketmachine.statemachine;

import com.homework.ticketmachine.models.TicketType;

import java.math.BigDecimal;

/**
 * State: Money received, ready to dispense
 */
public class MoneyReceivedState implements ITicketMachineState {
    @Override
    public void selectTicket(TicketMachine context, TicketType ticket) {
        throw new IllegalStateException("Сначала завершите текущую транзакцию");
    }

    @Override
    public void insertMoney(TicketMachine context, BigDecimal amount) {
        throw new IllegalStateException("Достаточно средств, нажмите 'выдать'");
    }

    @Override
    public void dispenseTicket(TicketMachine context) {
        TicketType ticket = context.getSelectedTicket();
        BigDecimal change = context.getPayment().completeTransaction(ticket.getPrice());
        context.getInventory().dispense(ticket);

        System.out.println(String.format("Билет выдан: %s", ticket.getName()));
        if (change.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println(String.format("Сдача: %.2f₽", change));
        }

        context.setState(new TicketDispensedState());
        context.reset();
    }

    @Override
    public void cancel(TicketMachine context) {
        context.setState(new TransactionCanceledState());
        context.cancel();
    }
}
