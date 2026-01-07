package vendingmachine.exception;

public enum ErrorMessage {
    EMPTY_INPUT("빈 값을 입력할 수 없습니다."),
    INVALID_FORMAT("잘못된 형식을 입력하였습니다."),
    NOT_NUMBER("금액은 숫자여야 합니다."),
    NOT_MOD_TEN("10원으로 나눠떨어지지 않습니다."),
    OUT_OF_STOCK("선택한 상품의 재고가 없습니다."),
    LACK_OF_MONEY("선택한 상품의 가격보다 잔액이 적습니다.");


    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = PREFIX + message;
    }

    public String getMessage() {
        return message;
    }
}