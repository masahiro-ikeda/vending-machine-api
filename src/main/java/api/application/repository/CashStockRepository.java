package api.application.repository;

import api.domain.model.cash.CashStocks;

public interface CashStockRepository {

  /**
   * 保有現金を取得.
   *
   * @return 保有現金
   */
  CashStocks fetch();

  /**
   * 保有現金の更新.
   *
   * @param cashStocks 保有現金
   */
  void store(CashStocks cashStocks);
}
