package presenter;

public class RepaymentPresenter {

  /**
   * 返金のメッセージをコンソールに表示.
   *
   * @param repaymentAmount 返金金額
   */
  public void showMessage(int repaymentAmount) {
    System.out.println( repaymentAmount + "円を返金しました。" );
    System.out.println();
  }
}
