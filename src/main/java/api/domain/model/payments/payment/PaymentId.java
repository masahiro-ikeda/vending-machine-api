package api.domain.model.payments.payment;

import java.util.UUID;

public class PaymentId {
  private final UUID paymentId;

  /**
   * 新規生成.
   */
  public PaymentId() {
    this.paymentId = UUID.randomUUID();
  }

  /**
   * 復元.
   *
   * @param paymentId 文字列のUUID
   */
  public PaymentId(String paymentId) {
    this.paymentId = UUID.fromString( paymentId );
  }

  /**
   * @return 文字列の値
   */
  public String value() {
    return this.paymentId.toString();
  }
}
