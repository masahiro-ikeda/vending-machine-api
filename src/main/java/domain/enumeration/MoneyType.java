package domain.enumeration;

/**
 * 自動販売機で利用可能な貨幣を列挙.
 */
public enum MoneyType {
  TEN( 10 ),
  FIFTY( 50 ),
  HUNDRED( 100 ),
  FIVE_HUNDRED( 500 ),
  THOUSAND( 1000 );

  private final int amount;

  MoneyType(int amount) {
    this.amount = amount;
  }

  public int getValue() {
    return this.amount;
  }

  public static MoneyType getMoneyType(int amount) {
    for (MoneyType type : MoneyType.values()) {
      if (type.getValue() == amount) {
        return type;
      }
    }
    return null;
  }
}
