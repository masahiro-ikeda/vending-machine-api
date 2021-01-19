package presenter;

public class PurchasePresenter {

  /**
   * 購入後のメッセージをコンソールに表示.
   *
   * @param drinkName    　購入したドリンク名
   * @param changeAmount 　お釣りの金額
   */
  public void showMessage(String drinkName, int changeAmount) {

    // 購入したドリンクを表示
    System.out.println( drinkName + "を購入しました。" );
    System.out.println();

    // おつりを表示
    System.out.println( "おつりは " + changeAmount + " 円です。" );
    System.out.println();
  }
}
