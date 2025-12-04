package state;

import machine.TicketMachine;

public class TransactionCanceledState implements TicketMachineState {
    
    private static final TransactionCanceledState INSTANCE = new TransactionCanceledState();
    
    private TransactionCanceledState() {}
    
    public static TransactionCanceledState getInstance() {
        return INSTANCE;
    }
    
    @Override
    public void selectTicket(TicketMachine machine, String ticketType) {
        System.out.println("Дождитесь завершения отмены транзакции");
    }
    
    @Override
    public void insertMoney(TicketMachine machine, int amount) {
        System.out.println("Дождитесь завершения отмены транзакции");
    }
    
    @Override
    public void dispenseTicket(TicketMachine machine) {
        System.out.println("Транзакция отменена");
    }
    
    @Override
    public void cancel(TicketMachine machine) {
        System.out.println("Транзакция уже отменена");
    }
    
    @Override
    public void returnChange(TicketMachine machine) {
        System.out.println("Средства уже возвращены");
    }
    
    @Override
    public String getStateName() {
        return "TransactionCanceled (Транзакция отменена)";
    }
}
