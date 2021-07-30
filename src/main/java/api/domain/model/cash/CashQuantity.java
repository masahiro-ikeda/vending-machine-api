package api.domain.model.cash;

import api.domain.model.cash.inout.CashInoutQuantity;

/**
 * 現金の枚数を表す値オブジェクト.
 */
public class CashQuantity {

  // 数量
  private final int value;

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
