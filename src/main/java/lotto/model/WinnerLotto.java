package lotto.model;

import java.util.Arrays;
import java.util.List;
import lotto.exception.CustomIllegalArgumentException;
import lotto.utils.StringUtils;

public class WinnerLotto {

    private Lotto lotto;
    private LottoNumber bonusNumber;

    public void addLotto(String input) {
        List<Integer> numbers = parseNumbers(input);
        this.lotto = new Lotto(numbers);
    }

    public void addBonusNumber(String input) {
        LottoNumber bonusNumber = LottoNumber.from(parseNumber(input));
        validDuplicateNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    public Reward getReward(Lotto lottoTicket) {
        int correctNumber = lottoTicket.countCorrectNumber(lotto);
        boolean hasBonusNumber = lottoTicket.hasBonusNumber(bonusNumber);

        return Reward.getReward(correctNumber, hasBonusNumber);
    }


    private void validDuplicateNumber(LottoNumber lottoNumber) {
        lotto.validExistNumber(lottoNumber);
    }

    private List<Integer> parseNumbers(String input) {
        try {
            return Arrays.stream(StringUtils.split(",", input, 6))
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER);
        }
    }

    private int parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new CustomIllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER);
        }
    }
}
