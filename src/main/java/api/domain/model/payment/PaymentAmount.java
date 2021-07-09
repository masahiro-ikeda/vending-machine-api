package api.domain.model.payment;

/**
 * 支払い合計金額.
 */
public class PaymentAmount {

  private final int value;

  /**
   * コンストラクタ.
   *
   * @param
   */
  public PaymentAmount(Payments payments) {
    var list =  payments.payments();
    this.value = list.stream().mapToInt( payment -> payment.getYenCurrency().value() ).sum();
  }

  /**
   * 投入済み金額の合計を取得.
   *
   * @return 投入済み金額の合計
   */
  public int totalPaymentAmount() {
    return value;
  }
}
