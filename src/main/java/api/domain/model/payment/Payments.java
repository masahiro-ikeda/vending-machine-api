package api.domain.model.payment;

import lombok.AllArgsConstructor;

import java.util.List;

/**
 * 支払い.
 */
@AllArgsConstructor
public class Payments {
  private final List<Payment> payments;

  /**
   * 支払いリスト.
   */
  public List<Payment> payments() {
    return List.copyOf(payments);
  }

  /**
   * 支払いの初期化.
   */
  public void reset() {
    payments.clear();
  }
}
