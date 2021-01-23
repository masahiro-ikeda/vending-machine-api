package controler;

import usecase.PaymentUseCase;
import usecase.PurchaseUseCase;
import usecase.RepaymentUseCase;

import java.util.Scanner;

public class OrderController {

  /**
   * 命令を受け付ける.
   * 命令は"コマンド パラメータ"の2語になるようにする
   */
  public void receive() {

    Scanner scan = new Scanner( System.in );

    // 必要なインスタンスを生成
    PaymentUseCase paymentUseCase = new PaymentUseCase();
    PurchaseUseCase purchaseUseCase = new PurchaseUseCase();
    RepaymentUseCase repaymentUseCase = new RepaymentUseCase();

    // 以下、入力内容に応じて操作
    while (true) {
      String[] order = scan.nextLine().split( " " );
      String strCommand = order[0];

      if (strCommand.equals( OrderType.PAY.getValue() )) {
        int amount = Integer.parseInt( order[1] );
        paymentUseCase.pay( amount );

      } else if (strCommand.equals( OrderType.BUY.getValue() )) {
        int drinkId = Integer.parseInt( order[1] );
        purchaseUseCase.buy( drinkId );

      } else if (strCommand.equals( OrderType.REPAY.getValue() )) {
        repaymentUseCase.repay();

      } else {
        // TODO エラーメッセージどうする？
        throw new IllegalArgumentException( "不正な操作です。" );
      }
    }
  }
}
