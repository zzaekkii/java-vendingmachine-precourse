package vendingmachine.domain;

import vendingmachine.exception.ErrorMessage;

public class Product {
    private final String name;
    private final int price;
    private int quantity;

    public Product(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void sold(int money) {
        if (quantity == 0) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_STOCK.getMessage());
        }

        if (money < price) {
            throw new IllegalArgumentException(ErrorMessage.LACK_OF_MONEY.getMessage());
        }

        this.quantity -= 1;
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
}
