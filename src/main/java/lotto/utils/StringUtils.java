package lotto.utils;

import lotto.exception.CustomIllegalArgumentException;
import lotto.model.ErrorMessage;

public class StringUtils {


    private StringUtils() {
    }

    public static void validInput(String input) {
        if (input == null || input.isBlank()) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    public static String[] split(String regex, String input, Integer fieldCount) {
        if (regex == null || input == null || input.isBlank() || input.endsWith(regex)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }

        String[] split = input.split(regex);
        if (fieldCount != null && split.length != fieldCount) {
            throw new CustomIllegalArgumentException(ErrorMessage.LOTTO_NUMBERS_SIZE);
        }
        return split;
    }

}