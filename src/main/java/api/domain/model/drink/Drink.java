package api.domain.model.drink;

import api.domain.model.payment.PaymentAmount;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Drink {
  private final int drinkId;
  private final Price drinkPrice;
  private final Quantity drinkQuantity;

  /**
   * 購入可能か？
   *
   * @param paymentAmount 支払金額
   * @return 購入可能な場合true
   */
  public boolean canPurchase(PaymentAmount paymentAmount, Quantity orderQuantity) {

    // 価格チェック
    if (paymentAmount.totalPaymentAmount() < drinkPrice.value()) {
      return false;
    }

    // 在庫チェック
    if (!drinkQuantity.canShip(orderQuantity)) {
      return false;
    }

    return true;
  }
}
