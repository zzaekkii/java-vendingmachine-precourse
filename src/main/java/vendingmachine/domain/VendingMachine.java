package vendingmachine.domain;

import java.util.HashMap;
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

    public Map<Coin, Integer> returnCharge() {
        // 잔액(money)만큼 최소 동전 개수로 잔돈 생성 - 한국 동전 그리디로 가능함
        Map<Coin, Integer> charges = new HashMap<>();
        for (Coin coin : Coin.values()) {
            charges.put(coin, 0);
            if (coins.get(coin) == 0) {
                continue;
            }
            if (coin.getAmount() > money) {
                continue;
            }

            int count = money / coin.getAmount();
            charges.put(coin, count);
            money -= coin.getAmount() * count;

            // 이번 문제에선 필요없지만, 그래도 자판기 실제 보유량에서 차감되도록 반영
            coins.put(coin, coins.get(coin) - count);
        }
        return charges;
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
