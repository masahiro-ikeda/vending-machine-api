package api.domain.valueobject;

/**
 * 数量を表す値オブジェクト.
 */
public class Quantity {

  // 数量
  private final int value;

  /**
   * コンストラクタ.
   *
   * @param value 数量値
   */
  public Quantity(int value) {
    if (value < 0) {
      throw new IllegalArgumentException("Quantity Not Permit Minus.");
    }
    this.value = value;
  }

  /**
   * 増やす.
   *
   * @param increased 増分
   */
  public Quantity increase(Quantity increased) {
    return new Quantity(this.value + increased.value);
  }

  /**
   * 減らす.
   *
   * @param decreased 減分
   */
  public Quantity decrease(Quantity decreased) {

    // 保持数以上は減らせない
    if (decreased.value > this.value) {
      throw new RuntimeException("Quantity Shortage.");
    }
    return new Quantity(this.value - decreased.value);
  }

  /**
   * 数量の値をintで返す.
   *
   * @return 数量
   */
  public int value() {
    return value;
  }

  public boolean canShip(Quantity orderQuantity) {
    return this.value >= orderQuantity.value;
  }
}
