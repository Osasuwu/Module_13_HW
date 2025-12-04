package model;

public class TicketType {
    
    private final String name;
    private final int price;
    private int quantity;
    
    public TicketType(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getName() {
        return name;
    }
    
    public int getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }
    
    public boolean isAvailable() {
        return quantity > 0;
    }
    
    @Override
    public String toString() {
        return name + " - " + price + " руб. (остаток: " + quantity + ")";
    }
}
