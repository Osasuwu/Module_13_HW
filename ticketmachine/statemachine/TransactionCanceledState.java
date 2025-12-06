package com.homework.ticketmachine.statemachine;

import com.homework.ticketmachine.models.TicketType;

import java.math.BigDecimal;

/**
 * State: Transaction canceled
 */
public class TransactionCanceledState implements ITicketMachineState {
    @Override
    public void selectTicket(TicketMachine context, TicketType ticket) {
        throw new IllegalStateException("Транзакция отменена");
    }

    @Override
    public void insertMoney(TicketMachine context, BigDecimal amount) {
        throw new IllegalStateException("Транзакция отменена");
    }

    @Override
    public void dispenseTicket(TicketMachine context) {
        throw new IllegalStateException("Транзакция отменена");
    }

    @Override
    public void cancel(TicketMachine context) {
        BigDecimal refund = context.getPayment().cancelTransaction();
        if (refund.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println(String.format("Возврат средств: %.2f₽", refund));
        }
        System.out.println("Транзакция отменена");
        context.reset();
    }
}
