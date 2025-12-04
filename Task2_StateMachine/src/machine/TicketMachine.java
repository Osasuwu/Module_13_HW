package machine;

import model.TicketType;
import state.IdleState;
import state.TicketMachineState;

import java.util.HashMap;
import java.util.Map;

public class TicketMachine {
    
    private TicketMachineState currentState;
    private final Map<String, TicketType> ticketTypes;
    
    private String selectedTicketType;
    private int insertedAmount;
    private int requiredAmount;
    private int changeAmount;
    
    public TicketMachine() {
        this.currentState = IdleState.getInstance();
        this.ticketTypes = new HashMap<>();
        this.insertedAmount = 0;
        this.requiredAmount = 0;
        this.changeAmount = 0;
        initializeTickets();
    }
    
    private void initializeTickets() {
        ticketTypes.put("STANDARD", new TicketType("Стандартный", 50, 10));
        ticketTypes.put("EXPRESS", new TicketType("Экспресс", 100, 5));
        ticketTypes.put("VIP", new TicketType("VIP", 200, 3));
        ticketTypes.put("CHILD", new TicketType("Детский", 25, 15));
    }
    
    public void setState(TicketMachineState state) {
        System.out.println("[Переход: " + currentState.getStateName() + " -> " + state.getStateName() + "]");
        this.currentState = state;
    }
    
    public TicketMachineState getState() {
        return currentState;
    }
    
    public void selectTicket(String ticketType) {
        currentState.selectTicket(this, ticketType);
    }
    
    public void insertMoney(int amount) {
        currentState.insertMoney(this, amount);
    }
    
    public void dispenseTicket() {
        currentState.dispenseTicket(this);
    }
    
    public void cancel() {
        currentState.cancel(this);
    }
    
    public void returnChange() {
        currentState.returnChange(this);
    }
    
    public void showAvailableTickets() {
        System.out.println("\n=== Доступные билеты ===");
        for (TicketType ticket : ticketTypes.values()) {
            System.out.println("  " + ticket);
        }
        System.out.println("========================\n");
    }
    
    public void showCurrentState() {
        System.out.println("Текущее состояние: " + currentState.getStateName());
        if (selectedTicketType != null) {
            System.out.println("Выбранный билет: " + selectedTicketType);
            System.out.println("Требуемая сумма: " + requiredAmount + " руб.");
            System.out.println("Внесено: " + insertedAmount + " руб.");
        }
    }
    
    public boolean hasTickets(String ticketType) {
        TicketType ticket = ticketTypes.get(ticketType);
        return ticket != null && ticket.isAvailable();
    }
    
    public int getTicketPrice(String ticketType) {
        TicketType ticket = ticketTypes.get(ticketType);
        return ticket != null ? ticket.getPrice() : 0;
    }
    
    public void decrementTicketCount(String ticketType) {
        TicketType ticket = ticketTypes.get(ticketType);
        if (ticket != null) {
            ticket.decrementQuantity();
        }
    }
    
    public String getSelectedTicketType() {
        return selectedTicketType;
    }
    
    public void setSelectedTicketType(String selectedTicketType) {
        this.selectedTicketType = selectedTicketType;
    }
    
    public int getInsertedAmount() {
        return insertedAmount;
    }
    
    public void addInsertedAmount(int amount) {
        this.insertedAmount += amount;
    }
    
    public int getRequiredAmount() {
        return requiredAmount;
    }
    
    public void setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }
    
    public int getChangeAmount() {
        return changeAmount;
    }
    
    public void setChangeAmount(int changeAmount) {
        this.changeAmount = changeAmount;
    }
    
    public void resetTransaction() {
        this.selectedTicketType = null;
        this.insertedAmount = 0;
        this.requiredAmount = 0;
        this.changeAmount = 0;
    }
}
