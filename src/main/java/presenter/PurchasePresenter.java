package presenter;

import domain.model.cash.Cash;

import java.util.List;

public class PurchasePresenter {

  /**
   * 購入後のメッセージをコンソールに表示.
   *
   * @param drinkName 購入したドリンク名
   * @param changes   お釣りの枚数一覧
   */
  public void showMessage(String drinkName, List<Cash> changes) {

    // 購入したドリンクを表示
    System.out.println( drinkName + "を購入しました。" );
    System.out.println();

    // 返金するお金の枚数を表示
    changes.forEach( cash -> {
      String message = String.format( "%4s円%3s枚", cash.getMoneyType().getValue(), cash.getCashQuantity() );
      System.out.println( message );
    } );

    // おつりの金額計算
    int repaymentAmount = changes.stream().mapToInt( cash -> cash.getMoneyType().getValue() * cash.getCashQuantity() ).sum();
    System.out.println( repaymentAmount + "円を返金しました。" );
    System.out.println();
  }
}
