package api.presenter.console;

import api.domain.entity.cash.Cash;

import java.util.List;

public class RepaymentPresenter {

  /**
   * 返金のメッセージをコンソールに表示.
   *
   * @param changes おつり
   */
  public void showMessage(List<Cash> changes) {

    // 返金するお金の枚数を表示
    changes.forEach( cash -> {
      String message = String.format( "%s円%3s枚", cash.getMoneyType().getValue(), cash.getCashQuantity() );
      System.out.println( message );
    } );

    // おつりの金額計算
    int repaymentAmount = changes.stream().mapToInt( cash -> cash.getMoneyType().getValue() * cash.getCashQuantity() ).sum();
    System.out.println( repaymentAmount + "円を返金しました。" );
    System.out.println();
  }
}
