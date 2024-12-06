package lotto.model;

public enum ErrorMessage {
    INVALID_INPUT("잘못된 입력입니다. 다시 입력해 주세요."),
    INVALID_LOTTO_INPUT("천 원 단위의 올바른 입력만 가능합니다."),
    INVALID_LOTTO_NUMBER("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    DUPLICATED_LOTTO_NUMBER("중복된 숫자가 존재합니다."),
    LOTTO_NUMBERS_SIZE("로또 번호는 6개여야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}