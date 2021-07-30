package api.domain.model.payments;

import api.domain.model.payments.payment.Payment;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentTotalAmount {

  private final int paymentTotalAmount;

  /**
   * コンストラクタ.
   *
   * @param paymentList 支払記録
   */
  public PaymentTotalAmount(List<Payment> paymentList) {

    if (paymentList == null) {
      throw new IllegalArgumentException( "Cannot Sum Cause Payment Being Null." );
    }

    // 支払記録が未作成のとき
    if (paymentList.size() == 0) {
      this.paymentTotalAmount = 0;
      return;
    }

    // 支払合計の計算
    var sortedList = paymentList.stream().sorted( Comparator.comparing( Payment::paymentAt ) ).collect( Collectors.toList() );
    this.paymentTotalAmount = sortedList.stream().mapToInt( payment -> {
      switch (payment.paymentType()) {
        case PAY:
          return payment.paymentAmount().value();
        case REPAY:
        case PURCHASE:
        case CHANGE:
          return payment.paymentAmount().value() * -1;
        default:
          throw new RuntimeException( "PaymentTypeの未実装選択肢あり" );
      }
    } ).sum();
  }

  /**
   * @return 支払金額合計.
   */
  public int value() {
    return this.paymentTotalAmount;
  }
}
