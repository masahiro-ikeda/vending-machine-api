package api.application.repository;

import api.domain.model.payment.Payment;
import api.domain.model.payment.Payments;

/**
 * 支払の永続化層.
 */
public interface PaymentRepository {

  /**
   * 支払を追加.
   *
   * @param payment 支払
   */
  void store(Payment payment);

  /**
   * 支払記録を取得.
   */
  Payments fetch();
}
