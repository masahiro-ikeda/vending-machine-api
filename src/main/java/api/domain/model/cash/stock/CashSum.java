package api.domain.model.cash.stock;

import api.domain.model.cash.inout.CashInout;

import java.util.List;

/**
 * 貨幣毎の保持金額合計.
 */
public class CashSum {

  private final int cashSum;

  /**
   * コンストラクタ.
   *
   * @param cashInoutList 入出金記録
   */
  CashSum(List<CashInout> cashInoutList) {

    if (cashInoutList == null) {
      throw new IllegalArgumentException( "Cannot Sum Cause CashInout Being Null." );
    }

    // 入出金記録が未作成のとき
    if (cashInoutList.size() == 0) {
      this.cashSum = 0;
      return;
    }

    // 保持金額の計算
    this.cashSum = cashInoutList.stream().mapToInt( cashInout -> {
      switch (cashInout.cashInoutType()) {
        case IN:
          return cashInout.yenCurrency().value() * cashInout.cashInoutQuantity().value();
        case OUT:
          return cashInout.yenCurrency().value() * cashInout.cashInoutQuantity().value() * -1;
        default:
          throw new RuntimeException( "Undefined YenCurrency Type." );
      }
    } ).sum();
  }

  /**
   * @return 保持残高の整数値.
   */
  public int value() {
    return this.cashSum;
  }
}
