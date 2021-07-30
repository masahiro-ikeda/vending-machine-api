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
  private final List<Payment> paymentList;
  private PaymentTotalAmount paymentTotalAmount;

  /**
   * コンストラクタ.
   *
   * @param paymentList
   */
  public Payments(List<Payment> paymentList) {

    if (paymentList == null) {
      throw new IllegalArgumentException( "Payments Not Permit Null." );
    }
    this.paymentList = paymentList;
    this.paymentTotalAmount = new PaymentTotalAmount( paymentList );
  }

  /**
   * 支払.
   *
   * @param yenCurrency
   * @return PaymentTotalAmount 支払金額合計
   */
  public PaymentTotalAmount pay(YenCurrency yenCurrency) {

    // 新規支払インスタンスの生成
    Payment newPayment = PaymentFactory.newPay( yenCurrency );
    // リスト追加
    paymentList.add( newPayment );
    // 支払合計の再計算
    this.paymentTotalAmount = new PaymentTotalAmount( paymentList );

    return this.paymentTotalAmount;
  }

  /**
   * 購入.
   */
  public void purchase(DrinkPrice drinkPrice) {
    // 購入記録の生成
    Payment purchasePayment = PaymentFactory.newPurchasePay( drinkPrice );
    paymentList.add( purchasePayment );
    // 支払合計の再計算
    this.paymentTotalAmount = new PaymentTotalAmount( paymentList );
  }

  /**
   * 返金.
   */
  public ReturnAmount repay() {
    // 返金額インスタンスの生成
    ReturnAmount returnAmount = new ReturnAmount( this.paymentTotalAmount.value() );

    // 返金記録の作成
    Payment repayment = PaymentFactory.newRepay( this.paymentTotalAmount );
    paymentList.add( repayment );
    // 支払合計の再計算
    this.paymentTotalAmount = new PaymentTotalAmount( paymentList );

    return returnAmount;
  }

  /**
   * おつり.
   *
   * @return ReturnAmount おつり金額
   */
  public ReturnAmount change() {
    ReturnAmount returnAmount = new ReturnAmount( paymentTotalAmount.value() );

    // お釣り記録の生成
    Payment changePayment = PaymentFactory.newChange( paymentTotalAmount );
    paymentList.add( changePayment );
    // 支払合計の再計算
    this.paymentTotalAmount = new PaymentTotalAmount( paymentList );

    return returnAmount;
  }

  /**
   * @return PaymentTotalAmount 支払金額合計
   */
  public PaymentTotalAmount paymentTotalAmount() {
    return paymentTotalAmount;
  }

  /**
   * @return List<Payment> 支払記録リスト
   */
  public List<Payment> paymentList() {
    return List.copyOf( paymentList );
  }
}
