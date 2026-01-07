package vendingmachine.domain;

import java.util.List;
import java.util.Map;

public class VendingMachine {

    private final Map<Coin, Integer> coins;
    private final List<Product> products;
    private int money = 0;

    public VendingMachine(Map<Coin, Integer> coins, List<Product> products) {
        this.coins = coins;
        this.products = products;
    }

    public void putMoney(int money) {
        this.money = money;
    }

    public void sold(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                product.sold(money);
                money -= product.getPrice();
            }
        }
    }

    public boolean cantContinue() {
        if (outOfStock()) {
            return true;
        }
        return money < getLowestPrice();
    }

    public boolean isExistProduct(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    private boolean outOfStock() {
        int stocks = 0;
        for (Product product : products) {
            stocks += product.getQuantity();
        }
        return stocks == 0;
    }

    private int getLowestPrice() {
        int price = products.get(0).getPrice();
        for (Product product : products) {
            if (price > product.getPrice()) {
                price = product.getPrice();
            }
        }
        return price;
    }

    public int getMoney() {
        return money;
    }
}
