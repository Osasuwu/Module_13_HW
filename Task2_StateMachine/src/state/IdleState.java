package state;

import machine.TicketMachine;

public class IdleState implements TicketMachineState {
    
    private static final IdleState INSTANCE = new IdleState();
    
    private IdleState() {}
    
    public static IdleState getInstance() {
        return INSTANCE;
    }
    
    @Override
    public void selectTicket(TicketMachine machine, String ticketType) {
        if (!machine.hasTickets(ticketType)) {
            System.out.println("Билеты типа '" + ticketType + "' отсутствуют");
            return;
        }
        
        int price = machine.getTicketPrice(ticketType);
        if (price <= 0) {
            System.out.println("Неверный тип билета: " + ticketType);
            return;
        }
        
        machine.setSelectedTicketType(ticketType);
        machine.setRequiredAmount(price);
        System.out.println("Выбран билет: " + ticketType + ", цена: " + price + " руб.");
        System.out.println("Внесите деньги");
        machine.setState(WaitingForMoneyState.getInstance());
    }
    
    @Override
    public void insertMoney(TicketMachine machine, int amount) {
        System.out.println("Сначала выберите билет");
    }
    
    @Override
    public void dispenseTicket(TicketMachine machine) {
        System.out.println("Сначала выберите билет и внесите деньги");
    }
    
    @Override
    public void cancel(TicketMachine machine) {
        System.out.println("Нет активной транзакции для отмены");
    }
    
    @Override
    public void returnChange(TicketMachine machine) {
        System.out.println("Нет сдачи для возврата");
    }
    
    @Override
    public String getStateName() {
        return "Idle (Ожидание)";
    }
}
