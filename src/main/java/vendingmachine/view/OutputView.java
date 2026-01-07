package vendingmachine.view;

import java.util.Map;
import vendingmachine.domain.Coin;

public class OutputView {

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void printMachineAmountRequest() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
    }

    public void printMachineCoins(Map<Coin, Integer> coins) {
        System.out.println("\n자판기가 보유한 동전");
        for (Coin coin : Coin.values()) {
            System.out.println(coin.getAmount() + "원 - " + coins.get(coin) + "개");
        }
        System.out.println();
    }

    public void printRegisterProductsRequest() {
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
    }

    public void printPutMoneyRequest() {
        System.out.println("\n투입 금액을 입력해 주세요.");
    }

    public void printCurrentMoney(int money) {
        System.out.println("\n투입 금액: " + money + "원");
    }

    public void printProductNameRequest() {
        System.out.println("구매할 상품명을 입력해 주세요.");
    }

    public void printCharges(Map<Coin, Integer> charges) {
        System.out.println("잔돈");
        for (Coin coin : Coin.values()) {
            if (charges.get(coin) == 0) {
                continue;
            }
            System.out.println(coin.getAmount() + "원 - " + charges.get(coin) + "개");
        }
        System.out.println();
    }
}
