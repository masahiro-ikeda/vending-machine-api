package api.domain.payment;

import api.domain.YenCurrency;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 支払いの集約.
 */
public class Payments {
  // 支払記録一覧
  private final List<Payment> payments;

  /**
   * コンストラクタ.
   */
  public Payments(List<Payment> payments) {
    this.payments = payments;
  }

  /**
   * 支払い.
   */
  public void pay(YenCurrency yenCurrency) {
    Payment payment = new Payment(
        UUID.randomUUID().toString(),
        yenCurrency.value(),
        PaymentType.PAY,
        LocalDateTime.now()
    );
    payments.add(payment);
  }

  /**
   * 販売.
   */
  public void sale(int drinkPrice) {
    Payment payment = new Payment(
        UUID.randomUUID().toString(),
        drinkPrice,
        PaymentType.SALE,
        LocalDateTime.now()
    );
    payments.add(payment);
  }

  /**
   * 返金.
   */
  public void repay() {
    Payment payment = new Payment(
        UUID.randomUUID().toString(),
        totalAmount(),
        PaymentType.REPAY,
        LocalDateTime.now()
    );
    payments.add(payment);
  }

  /**
   * @return 支払金額合計
   */
  public int totalAmount() {
    var sorted = payments.stream().sorted(Comparator.comparing(Payment::paymentAt)).collect(Collectors.toList());
    int total = 0;
    for (Payment payment : sorted) {
      switch (payment.paymentType()) {
        case PAY:
          total += payment.paymentAmount();
          break;
        case REPAY:
        case SALE:
          total -= payment.paymentAmount();
          break;
        default:
          throw new IllegalStateException("Illegal paymentType.");
      }
    }
    return total;
  }

  /**
   * @return List<Payment> 支払記録
   */
  public List<Payment> payments() {
    return List.copyOf(payments);
  }
}
