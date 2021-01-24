package api.domain.repository;

import api.domain.entity.payment.PaymentHolder;

/**
 * RDBではなくRedisなどの一時メモリに実装する想定.
 */
public interface PaymentRepository {

  /**
   * 支払い記録を保存する.
   *
   * @param paymentHolder 支払いの集約
   */
  void store(PaymentHolder paymentHolder);

  /**
   * 支払記録を取得.
   */
  PaymentHolder fetch();
}
