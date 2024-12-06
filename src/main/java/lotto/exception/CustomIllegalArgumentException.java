package lotto.exception;

import lotto.model.ErrorMessage;

public class CustomIllegalArgumentException extends IllegalArgumentException {
    public CustomIllegalArgumentException(String message) {
        super(message);
    }

    public CustomIllegalArgumentException(ErrorMessage message) {
        super(message.getMessage());
    }
}