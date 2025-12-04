package state;

import machine.TicketMachine;

public class TicketDispensedState implements TicketMachineState {
    
    private static final TicketDispensedState INSTANCE = new TicketDispensedState();
    
    private TicketDispensedState() {}
    
    public static TicketDispensedState getInstance() {
        return INSTANCE;
    }
    
    @Override
    public void selectTicket(TicketMachine machine, String ticketType) {
        System.out.println("Заберите сдачу для начала новой транзакции");
    }
    
    @Override
    public void insertMoney(TicketMachine machine, int amount) {
        System.out.println("Заберите сдачу для начала новой транзакции");
    }
    
    @Override
    public void dispenseTicket(TicketMachine machine) {
        System.out.println("Билет уже выдан");
    }
    
    @Override
    public void cancel(TicketMachine machine) {
        System.out.println("Транзакция уже завершена. Заберите сдачу");
    }
    
    @Override
    public void returnChange(TicketMachine machine) {
        int change = machine.getChangeAmount();
        if (change > 0) {
            System.out.println("Сдача " + change + " руб. выдана");
            machine.setChangeAmount(0);
        } else {
            System.out.println("Сдача отсутствует");
        }
        machine.resetTransaction();
        System.out.println("Автомат готов к новой транзакции");
        machine.setState(IdleState.getInstance());
    }
    
    @Override
    public String getStateName() {
        return "TicketDispensed (Билет выдан)";
    }
}
