package lotto.model;

import java.util.HashSet;
import java.util.List;
import lotto.exception.CustomIllegalArgumentException;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = parseLottoNumbers(numbers);
        validate(lottoNumbers);
        this.numbers = lottoNumbers;
    }

    private void validate(List<LottoNumber> numbers) {
        if (numbers.size() != 6) {
            throw new CustomIllegalArgumentException(ErrorMessage.LOTTO_NUMBERS_SIZE);
        }

        if (new HashSet<>(numbers).size() != 6) {
            throw new CustomIllegalArgumentException(ErrorMessage.DUPLICATED_LOTTO_NUMBER);
        }
    }

    private List<LottoNumber> parseLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .map(LottoNumber::from)
                .toList();
    }

    // TODO: 추가 기능 구현
}
