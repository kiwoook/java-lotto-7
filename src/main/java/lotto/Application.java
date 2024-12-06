package lotto;

import lotto.controller.LottoController;
import lotto.view.InputViewer;
import lotto.view.OutputViewer;

public class Application {
    public static void main(String[] args) {
        InputViewer inputViewer = new InputViewer();
        OutputViewer outputViewer = new OutputViewer();
        LottoController lottoController = new LottoController(inputViewer, outputViewer);

        lottoController.execute();
    }
}
