package lotto.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import lotto.exception.CustomIllegalArgumentException;
import lotto.utils.StringUtils;

public class Money {

    private static final BigDecimal PERCENT = new BigDecimal(100);
    private static final long TICKET_PRICE = 1000;

    private final long value;

    public Money(long value) {
        this.value = value;
    }

    public static Money from(String input) {
        return new Money(parseMoney(input));
    }

    public static long parseMoney(String input) {
        try {
            StringUtils.validInput(input);
            BigInteger money = new BigInteger(input);
            validMoney(money);
            return money.longValue();
        } catch (NumberFormatException e) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_INPUT);
        }
    }

    public static void validMoney(BigInteger money) {
        if (money.compareTo(new BigInteger(String.valueOf((Long.MAX_VALUE)))) > 0) {
            throw new CustomIllegalArgumentException(ErrorMessage.IMPOSSIBLE_PROCESS_INPUT);
        }

        if (money.compareTo(BigInteger.valueOf(TICKET_PRICE)) < 0) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_MONEY_INPUT);
        }

        if (!money.mod(BigInteger.valueOf(TICKET_PRICE)).equals(BigInteger.ZERO)) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_MONEY_INPUT);
        }
    }

    public long getLottoTicketCount() {
        return value / TICKET_PRICE;
    }

    public String getProfitPercent(long totalRewardPrice) {
        BigDecimal rewardPrice = new BigDecimal(totalRewardPrice);
        BigDecimal money = new BigDecimal(value);

        BigDecimal bigDecimal = rewardPrice.multiply(PERCENT).divide(money, 1, RoundingMode.HALF_UP);

        return bigDecimal.toString();
    }


}
