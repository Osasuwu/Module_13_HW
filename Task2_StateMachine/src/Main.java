import machine.TicketMachine;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        TicketMachine machine = new TicketMachine();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("==============================================");
        System.out.println("    АВТОМАТ ПО ПРОДАЖЕ БИЛЕТОВ");
        System.out.println("==============================================");
        
        boolean running = true;
        
        while (running) {
            printMenu();
            String input = scanner.nextLine().trim();
            
            System.out.println();
            
            switch (input) {
                case "1":
                    machine.showAvailableTickets();
                    break;
                    
                case "2":
                    System.out.print("Введите тип билета (STANDARD/EXPRESS/VIP/CHILD): ");
                    String ticketType = scanner.nextLine().trim().toUpperCase();
                    machine.selectTicket(ticketType);
                    break;
                    
                case "3":
                    System.out.print("Введите сумму: ");
                    try {
                        int amount = Integer.parseInt(scanner.nextLine().trim());
                        machine.insertMoney(amount);
                    } catch (NumberFormatException e) {
                        System.out.println("Некорректная сумма");
                    }
                    break;
                    
                case "4":
                    machine.dispenseTicket();
                    break;
                    
                case "5":
                    machine.returnChange();
                    break;
                    
                case "6":
                    machine.cancel();
                    break;
                    
                case "7":
                    machine.showCurrentState();
                    break;
                    
                case "8":
                    runDemoScenario(machine);
                    break;
                    
                case "0":
                    running = false;
                    System.out.println("Завершение работы автомата");
                    break;
                    
                default:
                    System.out.println("Неверная команда");
            }
            
            System.out.println();
        }
        
        scanner.close();
    }
    
    private static void printMenu() {
        System.out.println("----------------------------------------------");
        System.out.println("1. Показать доступные билеты");
        System.out.println("2. Выбрать билет");
        System.out.println("3. Внести деньги");
        System.out.println("4. Получить билет");
        System.out.println("5. Забрать сдачу");
        System.out.println("6. Отменить транзакцию");
        System.out.println("7. Показать текущее состояние");
        System.out.println("8. Демонстрационный сценарий");
        System.out.println("0. Выход");
        System.out.println("----------------------------------------------");
        System.out.print("Выберите действие: ");
    }
    
    private static void runDemoScenario(TicketMachine machine) {
        System.out.println("\n========== ДЕМОНСТРАЦИЯ РАБОТЫ АВТОМАТА ==========\n");
        
        System.out.println("--- Сценарий 1: Успешная покупка билета ---");
        machine.showAvailableTickets();
        
        System.out.println("> Выбираем стандартный билет (50 руб.)");
        machine.selectTicket("STANDARD");
        
        System.out.println("\n> Вносим 30 руб.");
        machine.insertMoney(30);
        
        System.out.println("\n> Вносим еще 30 руб.");
        machine.insertMoney(30);
        
        System.out.println("\n> Получаем билет");
        machine.dispenseTicket();
        
        System.out.println("\n> Забираем сдачу");
        machine.returnChange();
        
        System.out.println("\n--- Сценарий 2: Отмена транзакции ---");
        
        System.out.println("\n> Выбираем VIP билет (200 руб.)");
        machine.selectTicket("VIP");
        
        System.out.println("\n> Вносим 100 руб.");
        machine.insertMoney(100);
        
        System.out.println("\n> Отменяем транзакцию");
        machine.cancel();
        
        System.out.println("\n--- Сценарий 3: Попытка выбора несуществующего билета ---");
        
        System.out.println("\n> Пытаемся выбрать несуществующий тип билета");
        machine.selectTicket("PREMIUM");
        
        System.out.println("\n========== ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА ==========\n");
    }
}
