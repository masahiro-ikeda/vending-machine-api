package api.presenter.console;

import api.domain.entity.drink.DrinkDisplay;

import java.util.List;

public class PaymentPresenter {

  /**
   * 支払い後のメッセージをコンソールに表示.
   *
   * @param totalAmount   支払金額の合計
   * @param drinkDisplays 陳列するドリンク一覧
   */
  public void showMessage(int totalAmount, List<DrinkDisplay> drinkDisplays) {

    System.out.println( "合計金額は" + totalAmount + "円です。" );
    System.out.println();

    long saleableDrinkCount = drinkDisplays.stream().filter( DrinkDisplay::isSaleable ).count();
    if (saleableDrinkCount == 0) {
      System.out.println( "お金が足りないので購入できるドリンクはありません。" );
      System.out.println();
      return;
    }

    System.out.println( "購入するドリンクを選んでください。" );
    System.out.println();
    for (DrinkDisplay drinkDisplay : drinkDisplays) {
      if (!drinkDisplay.isSaleable()) {
        continue;
      }
      String item = String.format(
          "%3s %10s %3s円",
          drinkDisplay.getDrinkId(),
          drinkDisplay.getDrinkName(),
          drinkDisplay.getDrinkPrice()
      );
      System.out.println( item );
    }
  }
}
