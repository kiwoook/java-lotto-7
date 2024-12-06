package lotto.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import lotto.exception.CustomIllegalArgumentException;

public class Money {

    private final long value;

    public Money(long value) {
        this.value = value;
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

    public long getLottoTicketCount() {
        return value / 1000;
    }

    public String getProfitPercent(long totalRewardPrice) {
        BigDecimal percent = new BigDecimal(100);
        BigDecimal rewardPrice = new BigDecimal(totalRewardPrice);
        BigDecimal money = new BigDecimal(value);

        BigDecimal bigDecimal = rewardPrice.multiply(percent).divide(money, 1, RoundingMode.HALF_UP);

        return bigDecimal.toString();
    }


}
