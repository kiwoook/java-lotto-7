package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputViewer {

    public String promptMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return Console.readLine();
    }

    public String promptWinnerLotto() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
        return Console.readLine();
    }

    public String promptBonusNumber() {
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
        return Console.readLine();
    }
}
