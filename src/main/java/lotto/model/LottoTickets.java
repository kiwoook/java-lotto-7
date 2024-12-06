package lotto.model;

import static lotto.utils.Constants.ENTER;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

public class LottoTickets {

    private final Map<Reward, Integer> rewardMap;
    private final List<Lotto> items;

    public LottoTickets() {
        this.rewardMap = new LinkedHashMap<>();
        this.items = new ArrayList<>();
        initMap();
    }


    private void initMap() {
        // 순서 바꿈 이슈 때문에 이따구로 짬...
        List<Reward> rewardList = new ArrayList<>(Arrays.stream(Reward.values()).toList());
        rewardList.reversed();

        for (Reward reward : rewardList) {
            if (!reward.equals(Reward.NONE)) {
                rewardMap.put(reward, 0);
            }
        }
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


    public void process(WinnerLotto winnerLotto) {
        for (Lotto lottoTicket : items) {
            Reward reward = winnerLotto.getReward(lottoTicket);
            if (reward.equals(Reward.NONE)) {
                continue;
            }

            rewardMap.merge(reward, 1, Integer::sum);
        }
    }

    public String toResult() {
        StringJoiner joiner = new StringJoiner(ENTER);

        for (Entry<Reward, Integer> entry : rewardMap.entrySet()) {
            String message = entry.getKey().getMessage();
            long count = entry.getValue();
            joiner.add(message + " - " + count + "개");
        }

        return joiner.toString();
    }

    public Long totalRewardPrice() {
        long rewardPrice = 0;

        for (Entry<Reward, Integer> entry : rewardMap.entrySet()) {
            long price = entry.getKey().getPrice();
            long count = entry.getValue();

            rewardPrice += price * count;
        }

        return rewardPrice;
    }


}
