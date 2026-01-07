package vendingmachine.domain;

import java.util.List;
import java.util.Map;

public class VendingMachine {

    private final Map<Coin, Integer> coins;
    private final List<Product> products;
    private int balance = 0;

    public VendingMachine(Map<Coin, Integer> coins, List<Product> products) {
        this.coins = coins;
        this.products = products;
    }
}
