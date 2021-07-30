package api.domain.model.payments;

/**
 * 返金金額.
 */
public class ReturnAmount {

  private final int returnAmount;

  /**
   * コンストラクタ.
   *
   * @param returnAmount
   */
  public ReturnAmount(int returnAmount) {
    if (returnAmount < 0) {
      throw new IllegalArgumentException( "ReturnAmount Not Permit Minus." );
    }
    this.returnAmount = returnAmount;
  }

  /**
   * 金額を取得.
   *
   * @return 投入済み金額の合計
   */
  public int value() {
    return this.returnAmount;
  }
}
