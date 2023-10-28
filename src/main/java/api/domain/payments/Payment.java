package api.domain.payments;

import java.time.LocalDateTime;

/**
 * 支払い.
 */
public class Payment {
  private final String id;
  private final int paymentAmount;
  private final PaymentType paymentType;
  private final LocalDateTime paymentAt;

  /**
   * コンストラクタ.
   */
  public Payment(String id, int paymentAmount, PaymentType paymentType, LocalDateTime paymentAt) {
    this.id = id;
    if (paymentAmount < 0) {
      throw new IllegalArgumentException("PaymentAmount Not Permit Minus.");
    }
    this.paymentAmount = paymentAmount;
    this.paymentType = paymentType;
    this.paymentAt = paymentAt;
  }

  public String id() {
    return this.id;
  }

  public int paymentAmount() {
    return this.paymentAmount;
  }

  public PaymentType paymentType() {
    return this.paymentType;
  }

  public LocalDateTime paymentAt() {
    return this.paymentAt;
  }
}
