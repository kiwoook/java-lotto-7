package lotto.controller;

import lotto.model.LottoTickets;
import lotto.model.Money;
import lotto.utils.RecoveryUtils;
import lotto.view.InputViewer;
import lotto.view.OutputViewer;

public class LottoController {

    private final InputViewer inputViewer;
    private final OutputViewer outputViewer;
    private LottoTickets lottoTickets = new LottoTickets();

    public LottoController(InputViewer inputViewer, OutputViewer outputViewer) {
        this.inputViewer = inputViewer;
        this.outputViewer = outputViewer;
    }

    public void execute() {
        Money money = getMoney();
        createLottoTickets(money);
    }


    public Money getMoney() {
        return RecoveryUtils.executeWithRetry(inputViewer::promptMoney, Money::from);
    }

    public void createLottoTickets(Money money) {
        lottoTickets.create(money);
        outputViewer.printLottoTickets(money.getLottoTicketCount(), lottoTickets.toStatus());
    }


}
