package api.domain.cash;

public interface CashStockRepository {

  /**
   * 保有現金を取得.
   */
  CashStocks fetch();

  /**
   * 保有現金の更新.
   */
  void store(CashStocks cashStocks);
}
