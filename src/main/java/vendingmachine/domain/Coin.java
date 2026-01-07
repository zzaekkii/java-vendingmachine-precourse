package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vendingmachine.exception.ErrorMessage;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    // 추가 기능 구현
    public static Map<Coin, Integer> makeCoins(int price) {
        List<Integer> coinAmounts = new ArrayList<>();
        for (Coin coin : Coin.values()) {
            coinAmounts.add(coin.amount);
        }

        Map<Coin, Integer> coins = initializeCoins();
        while (price > 0) {
            Coin newCoin = makeCoin(coinAmounts);
            if (newCoin.amount > price) {
                continue;
            }
            coins.put(newCoin, coins.get(newCoin) + 1);
            price -= newCoin.amount;
        }

        return coins;
    }

    public int getAmount() {
        return amount;
    }

    private static Coin makeCoin(List<Integer> coinAmounts) {
        return fromAmount(Randoms.pickNumberInList(coinAmounts));
    }

    private static Coin fromAmount(int amount) {
        for (Coin coin : Coin.values()) {
            if (coin.amount == amount) {
                return coin;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.ETC.getMessage());
    }

    private static Map<Coin, Integer> initializeCoins() {
        Map<Coin, Integer> coins = new HashMap<>();
        for (Coin coin : Coin.values()) {
            coins.put(coin, 0);
        }
        return coins;
    }
}
