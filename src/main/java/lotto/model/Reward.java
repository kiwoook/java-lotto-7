package lotto.model;

import java.util.Arrays;

public enum Reward {

    FIRST(6, false, "6개 일치 (2,000,000,000원)", 2_000_000_000),
    SECOND(5, true, "5개 일치, 보너스 볼 일치 (30,000,000원)", 30_000_000),
    THIRD(5, false, "5개 일치 (1,500,000원)", 1_500_000),
    FOURTH(4, false, "4개 일치 (50,000원)", 50_000),
    FIFTH(3, false, "3개 일치 (5,000원)", 5_000),
    NONE(0, false, null, 0);


    private final int correctCount;
    private final boolean hasBonusNumber;
    private final String message;
    private final long price;

    Reward(int correctCount, boolean hasBonusNumber, String message, long price) {
        this.correctCount = correctCount;
        this.hasBonusNumber = hasBonusNumber;
        this.message = message;
        this.price = price;
    }

    public static Reward getReward(int correctCount, boolean hasBonusNumber) {
        return Arrays.stream(values()).filter(value -> value.filtered(correctCount, hasBonusNumber))
                .findFirst()
                .orElse(Reward.NONE);
    }


    public boolean filtered(int correctCount, boolean hasBonusNumber) {
        if (correctCount == 5) {
            return this.correctCount == correctCount && this.hasBonusNumber == hasBonusNumber;
        }

        if (hasBonusNumber) {
            correctCount += 1;
        }

        return this.correctCount <= correctCount;
    }

    public long getPrice() {
        return price;
    }

    public String getMessage() {
        return message;
    }
}
