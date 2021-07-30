package api.domain.model.payments;

import api.domain.model.YenCurrency;
import api.domain.model.payments.payment.Payment;
import api.domain.model.payments.payment.PaymentFactory;
import api.domain.model.payments.payment.PaymentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Paymentsモデルのユニットテスト")
class PaymentsTest {

  @DisplayName("Nullで初期化")
  @Test
  void testInitializeNull() {
    Assertions.assertThrows( IllegalArgumentException.class, () -> new Payments( null ) );
  }

  @DisplayName("空リストで初期化")
  @Test
  void testInitializeEmptyList() {
    var payments = new Payments( new ArrayList<>() );
    Assertions.assertEquals( 0, payments.paymentList().size() );
    Assertions.assertEquals( 0, payments.paymentTotalAmount().value() );
  }

  /**
   * テスト用の支払一覧.
   */
  private final List<Payment> testPaymentList = new ArrayList() {
    {
      add( PaymentFactory.newPay( YenCurrency.TEN ) );
      add( PaymentFactory.newPay( YenCurrency.FIFTY ) );
      add( PaymentFactory.newPay( YenCurrency.HUNDRED ) );
      add( PaymentFactory.newPay( YenCurrency.FIVE_HUNDRED ) );
      add( PaymentFactory.newPay( YenCurrency.THOUSAND ) );
    }
  };

  @DisplayName("支払記録で初期化")
  @Test
  void testInitializePaymentList() {
    var payments = new Payments( testPaymentList );

    Assertions.assertEquals( 5, payments.paymentList().size() );
    Assertions.assertEquals( 1660, payments.paymentTotalAmount().value() );
  }

  @DisplayName("支払記録の追加")
  @Test
  void testPay() {
    var payments = new Payments( new ArrayList<>() );
    payments.pay( YenCurrency.TEN );

    Assertions.assertEquals( 1, payments.paymentList().size() );
    Assertions.assertEquals( 10, payments.paymentTotalAmount().value() );
  }

  @DisplayName("返金の実施")
  @Test
  void testRepay() {
    var payments = new Payments( testPaymentList );
    payments.repay();

    Assertions.assertEquals( 6, payments.paymentList().size() );
    Assertions.assertEquals( 0, payments.paymentTotalAmount().value() );
    Payment repay = payments.paymentList().get( 5 );
    Assertions.assertEquals( PaymentType.REPAY, repay.paymentType() );
  }
}
