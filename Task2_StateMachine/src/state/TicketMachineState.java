package state;

import machine.TicketMachine;

public interface TicketMachineState {
    
    void selectTicket(TicketMachine machine, String ticketType);
    
    void insertMoney(TicketMachine machine, int amount);
    
    void dispenseTicket(TicketMachine machine);
    
    void cancel(TicketMachine machine);
    
    void returnChange(TicketMachine machine);
    
    String getStateName();
}
