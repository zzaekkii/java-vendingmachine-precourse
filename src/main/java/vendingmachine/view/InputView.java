package vendingmachine.view;


import camp.nextstep.edu.missionutils.Console;

public class InputView {

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
