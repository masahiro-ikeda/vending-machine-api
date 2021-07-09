package api.domain.model.payment;

/**
 * 自動販売機で使える貨幣.
 */
public enum YenCurrency {
  TEN(10),
  FIFTY(50),
  HUNDRED(100),
  FIVE_HUNDRED(500),
  THOUSAND(1000);

  // 額面
  private final int value;

  YenCurrency(int value) {
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
  public static YenCurrency of(int value) {
    for (YenCurrency yenCurrency : values()) {
      if (value == yenCurrency.value) {
        return yenCurrency;
      }
    }
    throw new IllegalArgumentException("Illegal Yen Currency.");
  }
}
