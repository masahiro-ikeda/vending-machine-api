package api.domain.model.cash.inout;

import api.domain.model.common.YenCurrency;

import java.time.LocalDateTime;

/**
 * 現金の入出金.
 */
public class CashInout {

  // 入出金ID
  private final CashInoutId cashInoutId;
  // 貨幣
  private final YenCurrency yenCurrency;
  // 入出金区分
  private final CashInoutType cashInoutType;
  // 入出金枚数
  private final CashInoutQuantity cashInoutQuantity;
  // 入出金日時
  private final LocalDateTime cashInoutAt;

  /**
   * コンストラクタ.
   *
   * @param cashInoutId
   * @param yenCurrency
   * @param cashInoutType
   * @param cashInoutQuantity
   * @param cashInoutAt
   */
  CashInout(CashInoutId cashInoutId, YenCurrency yenCurrency, CashInoutType cashInoutType, CashInoutQuantity cashInoutQuantity, LocalDateTime cashInoutAt) {
    this.cashInoutId = cashInoutId;
    this.yenCurrency = yenCurrency;
    this.cashInoutType = cashInoutType;
    this.cashInoutQuantity = cashInoutQuantity;
    this.cashInoutAt = cashInoutAt;
  }

  public CashInoutId cashInoutId() {
    return cashInoutId;
  }

  public YenCurrency yenCurrency() {
    return yenCurrency;
  }

  public CashInoutType cashInoutType() {
    return cashInoutType;
  }

  public CashInoutQuantity cashInoutQuantity() {
    return cashInoutQuantity;
  }

  public LocalDateTime cashInoutAt() {
    return cashInoutAt;
  }
}
