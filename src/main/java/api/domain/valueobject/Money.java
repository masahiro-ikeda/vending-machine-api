package api.domain.valueobject;

/**
 * 自動販売機で使える貨幣.
 */
public enum Money {
  TEN( 10 ),
  FIFTY( 50 ),
  HUNDRED( 100 ),
  FIVE_HUNDRED( 500 ),
  THOUSAND( 1000 );

  // 額面
  private final int value;

  Money(int value) {
    this.value = value;
  }

  public int value() {
    return this.value;
  }

  /**
   * 額面を指定して取得.
   *
   * @param value 額面
   * @return 合致する貨幣オブジェクト.
   */
  public static Money valueOf(int value) {
    for (Money money : values()) {
      if (value == money.value) {
        return money;
      }
    }
    // 合致しなければ例外throw
    throw new IllegalArgumentException();
  }
}
