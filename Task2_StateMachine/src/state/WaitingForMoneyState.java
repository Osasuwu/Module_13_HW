package state;

import machine.TicketMachine;

public class WaitingForMoneyState implements TicketMachineState {
    
    private static final WaitingForMoneyState INSTANCE = new WaitingForMoneyState();
    
    private WaitingForMoneyState() {}
    
    public static WaitingForMoneyState getInstance() {
        return INSTANCE;
    }
    
    @Override
    public void selectTicket(TicketMachine machine, String ticketType) {
        System.out.println("Билет уже выбран. Отмените текущую транзакцию для выбора другого билета");
    }
    
    @Override
    public void insertMoney(TicketMachine machine, int amount) {
        if (amount <= 0) {
            System.out.println("Сумма должна быть положительной");
            return;
        }
        
        machine.addInsertedAmount(amount);
        int remaining = machine.getRequiredAmount() - machine.getInsertedAmount();
        
        System.out.println("Внесено: " + amount + " руб.");
        System.out.println("Всего внесено: " + machine.getInsertedAmount() + " руб.");
        
        if (remaining > 0) {
            System.out.println("Осталось внести: " + remaining + " руб.");
        } else {
            System.out.println("Сумма достаточна для покупки");
            machine.setState(MoneyReceivedState.getInstance());
        }
    }
    
    @Override
    public void dispenseTicket(TicketMachine machine) {
        System.out.println("Недостаточно средств. Внесите еще: " + 
            (machine.getRequiredAmount() - machine.getInsertedAmount()) + " руб.");
    }
    
    @Override
    public void cancel(TicketMachine machine) {
        int refund = machine.getInsertedAmount();
        System.out.println("Транзакция отменена");
        if (refund > 0) {
            System.out.println("Возврат средств: " + refund + " руб.");
        }
        machine.setState(TransactionCanceledState.getInstance());
        machine.resetTransaction();
        machine.setState(IdleState.getInstance());
    }
    
    @Override
    public void returnChange(TicketMachine machine) {
        System.out.println("Сдача выдается после покупки билета");
    }
    
    @Override
    public String getStateName() {
        return "WaitingForMoney (Ожидание внесения денег)";
    }
}
