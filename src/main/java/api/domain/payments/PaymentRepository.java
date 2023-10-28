package api.domain.payments;

/**
 * 支払の永続化層.
 */
public interface PaymentRepository {

  /**
   * 支払を永続化.
   */
  void store(Payments payment);

  /**
   * 支払を追加.
   */
  void add(Payment payment);

  /**
   * 支払記録を取得.
   */
  Payments fetch();
}
