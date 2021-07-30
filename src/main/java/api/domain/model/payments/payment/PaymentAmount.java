package api.domain.model.payments.payment;

/**
 * 支払い金額.
 */
public class PaymentAmount {

  private final int paymentAmount;

  /**
   * コンストラクタ.
   *
   * @param paymentAmount
   */
  public PaymentAmount(int paymentAmount) {
    if (paymentAmount < 0) {
      throw new IllegalArgumentException( "PaymentAmount Not Permit Minus." );
    }
    this.paymentAmount = paymentAmount;
  }

  /**
   * 金額を取得.
   *
   * @return 投入済み金額の合計
   */
  public int value() {
    return this.paymentAmount;
  }
}
