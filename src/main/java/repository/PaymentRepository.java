package repository;

import domain.model.payment.Payments;

/**
 * RDBではなく一時メモリに実装する想定.
 */
public interface PaymentRepository {

  /**
   * 支払い記録を保存する.
   *
   * @param payments 支払いの集約
   */
  void store(Payments payments);

  /**
   * 支払記録を取得.
   */
  Payments fetch();

  /**
   * 支払い記録を解放.
   */
  void release();
}
