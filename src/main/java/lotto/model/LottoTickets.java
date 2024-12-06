package lotto.model;

import static lotto.utils.Constants.ENTER;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class LottoTickets {

    private final List<Lotto> items;

    public LottoTickets() {
        this.items = new ArrayList<>();
    }

    public void create(Money money) {
        for (int i = 0; i < money.getLottoTicketCount(); i++) {
            items.add(new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6)));
        }
    }

    public String toStatus() {
        StringJoiner joiner = new StringJoiner(ENTER);

        for (Lotto lotto : items) {
            joiner.add(lotto.toStatus());
        }

        return joiner.toString();
    }


}
