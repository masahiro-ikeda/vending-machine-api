package api.domain.model.payments;

import api.domain.model.common.YenCurrency;
import api.domain.model.drink.DrinkPrice;
import api.domain.model.payments.payment.Payment;
import api.domain.model.payments.payment.PaymentFactory;

import java.util.List;

/**
 * 支払記録の集約.
 */
public class Payments {
  // 支払記録一覧
  private final List<Payment> paymentList;

  /**
   * コンストラクタ.
   *
   * @param paymentList 支払記録一覧
   */
  public Payments(List<Payment> paymentList) {

    if (paymentList == null) {
      throw new IllegalArgumentException( "Payments Not Permit Null." );
    }
    this.paymentList = paymentList;
  }

  /**
   * 支払.
   *
   * @param yenCurrency 支払った貨幣
   */
  public void pay(YenCurrency yenCurrency) {

    // 新規支払インスタンスの生成
    Payment newPayment = PaymentFactory.newPay( yenCurrency );
    // リスト追加
    paymentList.add( newPayment );
  }

  /**
   * 購入.
   */
  public void purchase(DrinkPrice drinkPrice) {
    // 購入記録の生成
    Payment purchasePayment = PaymentFactory.newPurchasePay( drinkPrice );
    paymentList.add( purchasePayment );
  }

  /**
   * 返金.
   */
  public ReturnAmount repay() {
    // 返金前の支払金額合計
    var paymentTotalAmountBeforeRepay = new PaymentTotalAmount( paymentList );

    // 返金記録の作成
    Payment repayment = PaymentFactory.newRepay( paymentTotalAmountBeforeRepay );
    paymentList.add( repayment );

    return new ReturnAmount( paymentTotalAmountBeforeRepay.value() );
  }

  /**
   * おつり.
   *
   * @return ReturnAmount おつり金額
   */
  public ReturnAmount change() {
    // おつり返却前の支払金額合計
    var paymentTotalAmountBeforeChange = new PaymentTotalAmount( paymentList );

    // お釣り記録の生成
    Payment changePayment = PaymentFactory.newChange( paymentTotalAmountBeforeChange );
    paymentList.add( changePayment );

    return new ReturnAmount( paymentTotalAmountBeforeChange.value() );
  }

  /**
   * @return PaymentTotalAmount 支払金額合計
   */
  public PaymentTotalAmount paymentTotalAmount() {
    return new PaymentTotalAmount( paymentList );
  }

  /**
   * @return List<Payment> 支払記録リスト
   */
  public List<Payment> paymentList() {
    return List.copyOf( paymentList );
  }
}
