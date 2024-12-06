package lotto.model;

import static lotto.utils.Constants.LOTTO_END_NUMBER;
import static lotto.utils.Constants.LOTTO_START_NUMBER;

import java.util.Objects;
import lotto.exception.CustomIllegalArgumentException;

public class LottoNumber implements Comparable<LottoNumber> {

    private final Integer number;

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber from(int number) {
        validLottoNumber(number);
        return new LottoNumber(number);
    }

    public static void validLottoNumber(int number) {
        if (number < LOTTO_START_NUMBER || number > LOTTO_END_NUMBER) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER);
        }
    }

    @Override
    public String toString() {
        return number.toString();
    }

    @Override
    public int compareTo(LottoNumber o) {
        return number.compareTo(o.number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }
}
