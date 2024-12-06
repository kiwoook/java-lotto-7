package lotto.model;

import static lotto.utils.Constants.LOTTO_SIZE;

import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;
import lotto.exception.CustomIllegalArgumentException;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = parseLottoNumbers(numbers);
        validate(lottoNumbers);
        this.numbers = lottoNumbers;
    }

    private static List<LottoNumber> parseLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .map(LottoNumber::from)
                .toList();
    }

    private void validate(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new CustomIllegalArgumentException(ErrorMessage.LOTTO_NUMBERS_SIZE);
        }

        if (new HashSet<>(numbers).size() != LOTTO_SIZE) {
            throw new CustomIllegalArgumentException(ErrorMessage.DUPLICATED_LOTTO_NUMBER);
        }
    }

    public void validExistNumber(LottoNumber lottoNumber) {
        if (numbers.contains(lottoNumber)) {
            throw new CustomIllegalArgumentException(ErrorMessage.DUPLICATED_LOTTO_NUMBER);
        }
    }

    public int countCorrectNumber(Lotto winnerLotto) {
        return (int) winnerLotto.numbers.stream()
                .filter(numbers::contains)
                .count();
    }

    public boolean hasBonusNumber(LottoNumber bonusNumber) {
        return numbers.contains(bonusNumber);
    }


    public String toStatus() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");

        for (LottoNumber number : numbers) {
            joiner.add(number.toString());
        }

        return joiner.toString();
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "numbers=" + numbers +
                '}';
    }

}
