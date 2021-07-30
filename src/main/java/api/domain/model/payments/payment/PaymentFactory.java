package api.domain.model.payments.payment;

import api.domain.model.common.YenCurrency;
import api.domain.model.drink.DrinkPrice;
import api.domain.model.payments.PaymentTotalAmount;

import java.time.LocalDateTime;

/**
 * 支払記録を生成するファクトリー.
 */
public class PaymentFactory {

  /**
   * 支払記録の生成.
   *
   * @param yenCurrency
   * @return
   */
  public static Payment newPay(YenCurrency yenCurrency) {
    return new Payment(
        new PaymentId(),
        yenCurrency.paymentAmount(),
        PaymentType.PAY,
        LocalDateTime.now()
    );
  }

  /**
   * 返金記録の生成.
   *
   * @param paymentTotalAmount
   * @return
   */
  public static Payment newRepay(PaymentTotalAmount paymentTotalAmount) {
    return new Payment(
        new PaymentId(),
        new PaymentAmount( paymentTotalAmount.value() ),
        PaymentType.REPAY,
        LocalDateTime.now()
    );
  }

  /**
   * 支払記録の生成.
   *
   * @return
   */
  public static Payment newPurchasePay(DrinkPrice drinkPrice) {
    return new Payment(
        new PaymentId(),
        new PaymentAmount( drinkPrice.value() ),
        PaymentType.PURCHASE,
        LocalDateTime.now()
    );
  }

  /**
   * おつり返却記録の生成.
   *
   * @return
   */
  public static Payment newChange(PaymentTotalAmount paymentTotalAmount) {
    return new Payment(
        new PaymentId(),
        new PaymentAmount( paymentTotalAmount.value() ),
        PaymentType.CHANGE,
        LocalDateTime.now()
    );
  }

  /**
   * 記録の復元.
   *
   * @param paymentId
   * @param paymentAmount
   * @param paymentType
   * @param paymentAt
   * @return
   */
  public static Payment restore(String paymentId, int paymentAmount, String paymentType, LocalDateTime paymentAt) {
    return new Payment(
        new PaymentId( paymentId ),
        new PaymentAmount( paymentAmount ),
        PaymentType.valueOf( paymentType ),
        paymentAt
    );
  }
}
