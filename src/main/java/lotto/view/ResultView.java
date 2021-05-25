package lotto.view;

import lotto.common.WinningType;

import java.util.Map;

public class ResultView {
    public void printLottoTicketCount(int lottoTicketCount) {
        System.out.println(lottoTicketCount + "개를 구매했습니다.");
    }

    public void printLottoTickets(String lottoTicketsText) {
        System.out.println(lottoTicketsText);
    }

    public void printResultStatistics(Map<WinningType, Integer> gameResult) {
        System.out.println("\n당첨 통계\n---------");
        for(WinningType winningType : gameResult.keySet()) {
            if(winningType == WinningType.SECOND) {
                System.out.println(winningType.getMatchCount() + "개 일치, 보너스 볼 일치(" + winningType.getPrize() + "원)- "
                        + gameResult.get(winningType) +"개");
                continue;
            }

            if(winningType.getMatchCount() >= 3) {
                System.out.println(winningType.getMatchCount() + "개 일치 (" + winningType.getPrize() + "원)- "
                        + gameResult.get(winningType) +"개");
            }
        }
    }

    public void printResultProfit(double profit) {
        System.out.print("총 수익률은 " + profit + "입니다.");
        if(profit < 1) {
            System.out.print("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
    }
}
