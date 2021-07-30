package api.application.repository;

import api.domain.model.payments.Payments;

/**
 * 支払の永続化層.
 */
public interface PaymentRepository {

  /**
   * 支払を追加.
   *
   * @param payment 支払
   */
  void store(Payments payment);

  /**
   * 支払記録を取得.
   */
  Payments fetch();
}
