package api.domain.model.payment;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 支払い.
 */
@Getter
public class Payment {
  private final String paymentId;
  private final PaymentAmount paymentAmount;
  private final PaymentType paymentType;
  private final LocalDateTime paymentAt;

  /**
   * 支払記録の生成.
   *
   * @param yenCurrency
   * @return
   */
  public static Payment newPay(YenCurrency yenCurrency) {
    return new Payment(
        UUID.randomUUID().toString(),
        yenCurrency.amount(),
        PaymentType.PAY,
        LocalDateTime.now()
    );
  }

  /**
   * 返金記録の生成.
   *
   * @param paymentAmount
   * @return
   */
  public static Payment newRepay(PaymentAmount paymentAmount) {
    return new Payment(
        UUID.randomUUID().toString(),
        paymentAmount,
        PaymentType.REPAY,
        LocalDateTime.now()
    );
  }

  /**
   * コンストラクタ.
   */
  public Payment(String paymentId, PaymentAmount paymentAmount, PaymentType paymentType, LocalDateTime paymentAt) {
    this.paymentId = paymentId;
    this.paymentAmount = paymentAmount;
    this.paymentType = paymentType;
    this.paymentAt = paymentAt;
  }

  /**
   * 金額を取得.
   *
   * @return 金額
   */
  public int value() {
    return this.paymentAmount.value();
  }
}
