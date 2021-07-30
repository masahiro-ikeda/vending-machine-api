package api.domain.model.payments.payment;

import java.time.LocalDateTime;

/**
 * 支払い.
 */
public class Payment {
  private final PaymentId paymentId;
  private final PaymentAmount paymentAmount;
  private final PaymentType paymentType;
  private final LocalDateTime paymentAt;

  /**
   * コンストラクタ.
   */
  Payment(PaymentId paymentId, PaymentAmount paymentAmount, PaymentType paymentType, LocalDateTime paymentAt) {
    this.paymentId = paymentId;
    this.paymentAmount = paymentAmount;
    this.paymentType = paymentType;
    this.paymentAt = paymentAt;
  }

  /**
   * ↓ 以下、永続化用の値返却メソッド ↓.
   */
  public PaymentId paymentId() {
    return this.paymentId;
  }

  public PaymentAmount paymentAmount() {
    return this.paymentAmount;
  }

  public PaymentType paymentType() {
    return this.paymentType;
  }

  public LocalDateTime paymentAt() {
    return this.paymentAt;
  }
}
