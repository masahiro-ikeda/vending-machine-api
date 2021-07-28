package api.domain.model.payment;

import api.domain.model.payment.Payments;

/**
 * 支払い金額.
 */
public class PaymentAmount {

  private final int value;

  /**
   * コンストラクタ.
   *
   * @param
   */
  public PaymentAmount(int value) {
    if (value < 0) {
      throw new IllegalArgumentException( "PaymentAmount Not Permit Minus." );
    }
    this.value = value;
  }

  /**
   * 合計金額を取得するときのコンストラクタ.
   *
   * @param
   */
  public PaymentAmount(Payments payments) {
    var list = payments.payments();
    this.value = list.stream().mapToInt( payment -> payment.value() ).sum();
  }

  /**
   * 金額を取得.
   *
   * @return 投入済み金額の合計
   */
  public int value() {
    return this.value;
  }
}
