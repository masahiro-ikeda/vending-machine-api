package api.domain.repository;

import api.domain.entity.cash.CashManager;

public interface CashManagerRepository {

  /**
   * 保有現金を取得.
   *
   * @return 保有現金
   */
  CashManager fetch();

  /**
   * 保有現金の更新.
   *
   * @param cashManager 保有現金
   */
  void store(CashManager cashManager);
}
