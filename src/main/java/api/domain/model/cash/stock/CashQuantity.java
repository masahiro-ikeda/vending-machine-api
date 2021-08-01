package api.domain.model.cash.stock;

import api.domain.model.cash.inout.CashInout;

import java.util.List;

/**
 * 現金の枚数を表す値オブジェクト.
 */
public class CashQuantity {

  // 数量
  private final int value;

  /**
   * コンストラクタ.
   *
   * @param cashInoutList 貨幣入出金一覧
   */
  CashQuantity(List<CashInout> cashInoutList) {

    // Nullチェック
    if (cashInoutList == null) {
      throw new IllegalArgumentException( "Cannot Count Cause CashInout Being Null." );
    }

    // 入出金がない場合
    if (cashInoutList.size() == 0) {
      this.value = 0;
      return;
    }

    // 保持金額の計算
    this.value = cashInoutList.stream().mapToInt( cashInout -> {
      switch (cashInout.cashInoutType()) {
        case IN:
          return cashInout.cashInoutQuantity().value();
        case OUT:
          return cashInout.cashInoutQuantity().value() * -1;
        default:
          throw new RuntimeException( "Undefined YenCurrency Type." );
      }
    } ).sum();
  }

  /**
   * コンストラクタ.
   *
   * @param value 数量値
   */
  public CashQuantity(int value) {
    if (value < 0) {
      throw new IllegalArgumentException( "Quantity Not Permit Minus." );
    }
    this.value = value;
  }

  /**
   * 数量の値をintで返す.
   *
   * @return 数量
   */
  public int value() {
    return value;
  }
}
