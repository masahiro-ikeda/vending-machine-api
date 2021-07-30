package api.domain.model.cash;

import api.domain.model.cash.stock.CashStock;

import java.util.List;

public class CashTotalAmount {

  private final int cashTotalAmount;

  /**
   * コンストラクタ.
   *
   * @param cashStockList 入出金記録
   */
  public CashTotalAmount(List<CashStock> cashStockList) {

    if (cashStockList == null) {
      throw new IllegalArgumentException( "Cannot Sum Cause CashStock Being Null." );
    }

    // 保有する貨幣がない場合
    if (cashStockList.size() == 0) {
      this.cashTotalAmount = 0;
      return;
    }

    // 保有金額の合計
    this.cashTotalAmount = cashStockList.stream().mapToInt( cashStock -> cashStock.cashSum().value() ).sum();
  }

  /**
   * @return 保持残高の整数値.
   */
  public int value() {
    return this.cashTotalAmount;
  }
}
