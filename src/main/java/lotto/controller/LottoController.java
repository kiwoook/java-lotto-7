package lotto.controller;

import lotto.model.LottoTickets;
import lotto.model.Money;
import lotto.model.WinnerLotto;
import lotto.utils.RecoveryUtils;
import lotto.view.InputViewer;
import lotto.view.OutputViewer;

public class LottoController {

    private final InputViewer inputViewer;
    private final OutputViewer outputViewer;

    private final LottoTickets lottoTickets = new LottoTickets();
    private final WinnerLotto winnerLotto = new WinnerLotto();

    public LottoController(InputViewer inputViewer, OutputViewer outputViewer) {
        this.inputViewer = inputViewer;
        this.outputViewer = outputViewer;
    }

    public void execute() {
        Money money = getMoney();
        createLottoTickets(money);
        getWinnerLotto();
        result(money);
    }


    public Money getMoney() {
        return RecoveryUtils.executeWithRetry(inputViewer::promptMoney, Money::from);
    }

    public void createLottoTickets(Money money) {
        lottoTickets.create(money);
        outputViewer.printLottoTickets(money.getLottoTicketCount(), lottoTickets.toStatus());
    }


    public void getWinnerLotto() {
        RecoveryUtils.executeWithRetry(inputViewer::promptWinnerLotto, winnerLotto::addLotto);
        RecoveryUtils.executeWithRetry(inputViewer::promptBonusNumber, winnerLotto::addBonusNumber);
    }

    public void result(Money money) {
        lottoTickets.process(winnerLotto);
        Long totalRewardPrice = lottoTickets.totalRewardPrice();
        String profitPercent = money.getProfitPercent(totalRewardPrice);
        outputViewer.printResult(lottoTickets.toResult(), profitPercent);
    }
}
