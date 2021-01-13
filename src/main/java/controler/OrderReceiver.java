package controler;

import service.PaymentService;

import java.util.Scanner;

public class OrderReceiver {

  /**
   * 命令を受け付ける.
   * 命令は常に"コマンド パラメータ"の2語になるようにする
   */
  public void receive() {

    Scanner scan = new Scanner( System.in );

    // 必要なインスタンスを生成
    PaymentService paymentService = new PaymentService();

    // 以下、入力内容に応じて操作
    while (true) {
      String[] order = scan.nextLine().split( " " );

      String strCommand = order[0];
      CommandType type = CommandType.getType( strCommand );

      if (type == CommandType.PAYMENT) {
        int amount = Integer.parseInt( order[1] );
        paymentService.doPayment( amount );

      } else if (type == CommandType.SELECT) {
        String choise = order[1];

        // TODO 購入したときの処理を作成

      } else if (type == CommandType.STOP) {
        // TODO 購入をやめたときの処理を作成
      } else {
        throw new IllegalArgumentException();
      }
    }
  }
}
