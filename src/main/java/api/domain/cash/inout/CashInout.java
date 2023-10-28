package api.domain.cash.inout;

import api.domain.YenCurrency;

import java.time.LocalDateTime;

/**
 * 現金の入出金記録.
 */
public class CashInout {

  // 入出金ID
  private final String cashInoutId;
  // 貨幣
  private final YenCurrency yenCurrency;
  // 入出金区分
  private final CashInoutType cashInoutType;
  // 入出金枚数
  private final int cashInoutQuantity;
  // 入出金日時
  private final LocalDateTime cashInoutAt;

  /**
   * コンストラクタ.
   */
  public CashInout(String cashInoutId, YenCurrency yenCurrency, CashInoutType cashInoutType, int cashInoutQuantity, LocalDateTime cashInoutAt) {
    this.cashInoutId = cashInoutId;
    this.yenCurrency = yenCurrency;
    this.cashInoutType = cashInoutType;
    if (cashInoutQuantity < 0) {
      throw new IllegalArgumentException("CashInoutQuantity Not Permit Less Than Zero.");
    }
    this.cashInoutQuantity = cashInoutQuantity;
    this.cashInoutAt = cashInoutAt;
  }

  public String cashInoutId() {
    return cashInoutId;
  }

  public YenCurrency yenCurrency() {
    return yenCurrency;
  }

  public CashInoutType cashInoutType() {
    return cashInoutType;
  }

  public int cashInoutQuantity() {
    return cashInoutQuantity;
  }

  public LocalDateTime cashInoutAt() {
    return cashInoutAt;
  }
}
