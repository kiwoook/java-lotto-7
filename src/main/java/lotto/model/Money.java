package lotto.model;

import java.math.BigInteger;
import lotto.exception.CustomIllegalArgumentException;

public class Money {

    private final long money;

    public Money(long money) {
        this.money = money;
    }

    public static Money from(String input) {
        return new Money(parseMoney(input));
    }

    public static long parseMoney(String input) {
        try {
            long money = new BigInteger(input).longValue();
            validMoney(money);
            return money;
        } catch (NumberFormatException e) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }

    }

    public static void validMoney(long money) {
        if (money % 1000 != 0) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_LOTTO_INPUT);
        }
    }


}
