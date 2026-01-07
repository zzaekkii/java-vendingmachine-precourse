package vendingmachine.view;


import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Product;
import vendingmachine.exception.ErrorMessage;

public class InputView {

    private static final int MINIMUM_PRICE = Coin.COIN_100.getAmount();

    public int readMachineAmount() {
        String input = readAndValidate();

        int amount = validatePositiveInteger(input);

        validateModTen(amount);

        return amount;
    }

    public List<Product> readRegisterProducts() {
        String input = readAndValidate();

        List<Product> products = new ArrayList<>();
        String[] values = input.split(";");
        for (String value : values) {
            validateSeparator(value);

            String[] tokens = value.split(",");
            String name = tokens[0];
            int price = validatePositiveInteger(tokens[1]);
            validateProductPrice(price);
            int quantity = validatePositiveInteger(tokens[2]);

            products.add(new Product(name, price, quantity));
        }

        return products;
    }

    private void validateProductPrice(int price) {
        validateModTen(price);
        if (price >= MINIMUM_PRICE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PRODUCT_PRICE.getMessage());
        }
    }

    private static int validatePositiveInteger(String input) {
        int num = 0;
        try {
            num = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_POSITIVE_NUMBER.getMessage());
        }

        if (num <= 0) {
            throw new IllegalArgumentException(ErrorMessage.NOT_POSITIVE_NUMBER.getMessage());
        }

        return num;
    }

    private static void validateModTen(int amount) {
        if (amount % 10 > 0) {
            throw new IllegalArgumentException(ErrorMessage.NOT_MOD_TEN.getMessage());
        }
    }

    private static void validateSeparator(String value) {
        if (!value.startsWith("[") || !value.endsWith("]")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }

        if (value.contains(",,")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }

    private static String readAndValidate() {
        String input = readLine();

        nullCheck(input);

        input = input.trim();
        return input;
    }

    private static String readLine() {
        return Console.readLine();
    }

    private static void nullCheck(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }
    }
}
