package domain.model.payment;

import java.util.List;

/**
 * 支払い記録を保持する集約.
 */
public class PaymentHolder {

  private List<Payment> payments;

  /**
   * コンストラクタ.
   *
   * @param payments お金の投入履歴
   */
  public PaymentHolder(List<Payment> payments) {
    this.payments = payments;
  }

  /**
   * お金を投入する.
   *
   * @param payment 投入した金額
   */
  public void pay(Payment payment) {
    payments.add( payment );
  }

  /**
   * 投入済み金額の合計を取得.
   *
   * @return 投入済み金額の合計
   */
  public int getTotalAmount() {
    return payments.stream().mapToInt( Payment::getAmount ).sum();
  }

  /**
   * 支払いの初期化.
   */
  public void reset() {
    payments.clear();
  }

  /**
   * 支払い記録一覧を取得.
   *
   * @return 支払い記録一覧
   */
  public List<Payment> getPayments() {
    return payments;
  }
}
