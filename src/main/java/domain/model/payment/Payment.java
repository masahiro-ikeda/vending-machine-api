package domain.model.payment;

import domain.enumeration.MoneyType;

import java.time.LocalDateTime;

/**
 * お金の投入記録.
 */
public class Payment {

  private MoneyType moneyType;
  private LocalDateTime paidDatetime;

  /**
   * コンストラクタ.
   *
   * @param amount       金額
   * @param paidDatetime お金が投入された時間
   */
  Payment(int amount, LocalDateTime paidDatetime) {
    MoneyType moneyType = MoneyType.getMoneyType( amount );
    if (moneyType == null) {
      throw new IllegalArgumentException( "Illegal Money." );
    }
    this.moneyType = moneyType;
    this.paidDatetime = paidDatetime;
  }

  /**
   * 支払い金額を取得.
   *
   * @return 支払い金額
   */
  int getAmount() {
    return moneyType.getValue();
  }

  public MoneyType getMoneyType() {
    return moneyType;
  }

  public LocalDateTime getPaidDatetime() {
    return paidDatetime;
  }
}
