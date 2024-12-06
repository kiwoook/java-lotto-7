package lotto.view;

public class OutputViewer {

    private static final String ERROR_SIGN = "[ERROR] ";

    public void printError(Exception e) {
        System.out.println(ERROR_SIGN + e.getMessage());
    }

    public void printLottoTickets(long ticketCount, String ticketsStatus) {
        System.out.println();
        System.out.println(ticketCount + "개를 구매했습니다.");
        System.out.println(ticketsStatus);
    }

    public void printResult(String result, String profitPercent) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.println(result);
        System.out.println("총 수익률은 " + profitPercent + "%입니다.");
    }

}
