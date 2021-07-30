package api.application.repository;

import api.domain.model.cash.CashStock;

public interface CashStockRepository {

  /**
   * 保有現金を取得.
   *
   * @return 保有現金
   */
  CashStock fetch();

  /**
   * 保有現金の更新.
   *
   * @param cashStock 保有現金
   */
  void store(CashStock cashStock);
}
