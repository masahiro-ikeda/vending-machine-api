package api.domain.valueobject;

/**
 * 数量を表す値オブジェクト.
 */
public class Quantity {

  // 数量
  private final int quantity;

  /**
   * コンストラクタ.
   *
   * @param quantity 数量
   */
  public Quantity(int quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException( "Quantity Not Permit Zero And Minus." );
    }
    this.quantity = quantity;
  }

  /**
   * 増やす.
   *
   * @param increaseQuantity 増分
   */
  public Quantity increase(int increaseQuantity) {
    return new Quantity( quantity + increaseQuantity );
  }

  /**
   * 減らす.
   *
   * @param decreaseQuantity 減分
   */
  public Quantity decrease(int decreaseQuantity) {

    // 保持数以上は減らせない
    if (decreaseQuantity > quantity) {
      throw new RuntimeException( "Shortage Quantity." );
    }
    return new Quantity( quantity - decreaseQuantity );
  }

  /**
   * 数量の値をintで返す.
   *
   * @return 数量
   */
  public int intValue() {
    return quantity;
  }
}
