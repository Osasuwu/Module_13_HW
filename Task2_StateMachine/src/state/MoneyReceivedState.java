package state;

import machine.TicketMachine;

public class MoneyReceivedState implements TicketMachineState {
    
    private static final MoneyReceivedState INSTANCE = new MoneyReceivedState();
    
    private MoneyReceivedState() {}
    
    public static MoneyReceivedState getInstance() {
        return INSTANCE;
    }
    
    @Override
    public void selectTicket(TicketMachine machine, String ticketType) {
        System.out.println("Деньги уже внесены. Получите билет или отмените транзакцию");
    }
    
    @Override
    public void insertMoney(TicketMachine machine, int amount) {
        System.out.println("Достаточная сумма уже внесена. Получите билет");
    }
    
    @Override
    public void dispenseTicket(TicketMachine machine) {
        String ticketType = machine.getSelectedTicketType();
        
        if (!machine.hasTickets(ticketType)) {
            System.out.println("Билеты закончились. Возврат средств");
            cancel(machine);
            return;
        }
        
        machine.decrementTicketCount(ticketType);
        System.out.println("Билет '" + ticketType + "' выдан");
        machine.setState(TicketDispensedState.getInstance());
        
        int change = machine.getInsertedAmount() - machine.getRequiredAmount();
        if (change > 0) {
            machine.setChangeAmount(change);
            System.out.println("Сдача: " + change + " руб. Заберите сдачу");
        } else {
            machine.resetTransaction();
            System.out.println("Транзакция завершена");
            machine.setState(IdleState.getInstance());
        }
    }
    
    @Override
    public void cancel(TicketMachine machine) {
        int refund = machine.getInsertedAmount();
        System.out.println("Транзакция отменена");
        System.out.println("Возврат средств: " + refund + " руб.");
        machine.setState(TransactionCanceledState.getInstance());
        machine.resetTransaction();
        machine.setState(IdleState.getInstance());
    }
    
    @Override
    public void returnChange(TicketMachine machine) {
        System.out.println("Сначала получите билет");
    }
    
    @Override
    public String getStateName() {
        return "MoneyReceived (Деньги получены)";
    }
}
