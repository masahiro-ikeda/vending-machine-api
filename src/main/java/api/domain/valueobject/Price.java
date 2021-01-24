package api.domain.valueobject;

/**
 * 値段.
 */
public class Price {
  private final int price;

  /**
   * コンストラクタ.
   *
   * @param price 値段
   */
  public Price(int price) {
    // 1円以上のみ使用許可
    if (price <= 0) {
      throw new IllegalArgumentException( "" );
    }
    this.price = price;
  }

  /**
   * 値段をintで取得.
   *
   * @return 値段
   */
  public int intValue() {
    return price;
  }
}
