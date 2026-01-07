package vendingmachine.view;


import camp.nextstep.edu.missionutils.Console;
import vendingmachine.exception.ErrorMessage;

public class InputView {

    public int readMachineAmount() {
        String input = readAndValidate();

        int amount = validateInteger(input);

        validateModTen(amount);

        return amount;
    }

    private static int validateInteger(String input) {

        int amount = 0;
        try {
            amount = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_NUMBER.getMessage());
        }
        return amount;
    }

    private static void validateModTen(int amount) {
        if (amount % 10 > 0) {
            throw new IllegalArgumentException(ErrorMessage.NOT_MOD_TEN.getMessage());
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
