package api.application.repository;

import api.domain.model.purchase.sold.Sold;

public interface SoldRepository {

  /**
   * 購入記録の登録.
   *
   * @param sold 購入記録
   */
  void store(Sold sold);
}
