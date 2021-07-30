package api.domain.model.purchase.sold;

/**
 * 入出金枚数を表す値オブジェクト.
 */
public class SoldQuantity {

  // 数量
  private final int soldQuantity;

  /**
   * コンストラクタ.
   *
   * @param soldQuantity 数量値
   */
  public SoldQuantity(int soldQuantity) {
    if (soldQuantity < 0) {
      throw new IllegalArgumentException( "Quantity Not Permit Minus." );
    }
    this.soldQuantity = soldQuantity;
  }

  /**
   * 数量の値をintで返す.
   *
   * @return 数量
   */
  public int value() {
    return soldQuantity;
  }
}
