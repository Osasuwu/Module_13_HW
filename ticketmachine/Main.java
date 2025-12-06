package com.homework.ticketmachine;

import com.homework.ticketmachine.models.TicketType;
import com.homework.ticketmachine.services.InventoryService;
import com.homework.ticketmachine.services.PaymentService;
import com.homework.ticketmachine.services.TicketCatalogService;
import com.homework.ticketmachine.statemachine.TicketMachine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Main application class for ticket vending machine
 */
public class Main {
    public static void main(String[] args) {
        // Initialize catalog
        TicketCatalogService catalog = new TicketCatalogService();
        catalog.seed(
            new TicketType("S", "Стандартный билет", new BigDecimal("100")),
            new TicketType("P", "Премиум билет", new BigDecimal("200")),
            new TicketType("C", "Детский билет", new BigDecimal("50"))
        );

        // Initialize inventory
        InventoryService inventory = new InventoryService();
        Map<TicketType, Integer> stock = new HashMap<>();
        stock.put(catalog.find("S"), 10);
        stock.put(catalog.find("P"), 5);
        stock.put(catalog.find("C"), 3);
        inventory.seedStock(stock);

        // Initialize payment service and machine
        PaymentService payment = new PaymentService();
        TicketMachine machine = new TicketMachine(payment, inventory);

        System.out.println("=== Автомат по продаже билетов ===");
        System.out.println("Доступные билеты:");
        for (TicketType ticket : catalog.all()) {
            System.out.println(String.format("%s: %s (%.2f₽)", 
                ticket.getCode(), ticket.getName(), ticket.getPrice()));
        }

        // Test script
        Object[][] script = {
            {"select", "S"},
            {"insert", "100"},
            {"dispense", null},
            {"select", "P"},
            {"insert", "50"},
            {"cancel", null},
            {"select", "C"},
            {"insert", "20"},
            {"insert", "30"},
            {"dispense", null}
        };

        for (Object[] cmd : script) {
            String command = (String) cmd[0];
            String argument = (String) cmd[1];
            
            System.out.println("\n> " + command + (argument != null ? " " + argument : ""));

            try {
                switch (command) {
                    case "select":
                        if (argument == null || argument.trim().isEmpty()) {
                            System.out.println("Укажите код билета.");
                            continue;
                        }

                        TicketType ticket = catalog.find(argument);
                        if (ticket == null) {
                            System.out.println("Билет не найден.");
                            continue;
                        }

                        machine.selectTicket(ticket);
                        System.out.println(String.format("Выбран билет %s. Стоимость %.2f₽", 
                            ticket.getName(), ticket.getPrice()));
                        break;

                    case "insert":
                        if (argument == null || argument.trim().isEmpty()) {
                            System.out.println("Укажите корректную сумму.");
                            continue;
                        }

                        try {
                            BigDecimal amount = new BigDecimal(argument);
                            machine.insertMoney(amount);
                        } catch (NumberFormatException e) {
                            System.out.println("Укажите корректную сумму.");
                        }
                        break;

                    case "dispense":
                        machine.dispenseTicket();
                        break;

                    case "cancel":
                        machine.cancel();
                        break;

                    default:
                        System.out.println("Неизвестная команда.");
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Ошибка: " + ex.getMessage());
            }
        }
    }
}
