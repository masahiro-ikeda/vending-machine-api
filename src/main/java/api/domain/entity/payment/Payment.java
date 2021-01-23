package api.domain.entity.payment;

import api.domain.enumeration.MoneyType;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * お金の投入記録.
 */
@Getter
public class Payment {

  private MoneyType moneyType;
  private LocalDateTime paidDatetime;

  /**
   * コンストラクタ.
   *
   * @param amount 金額
   */
  public Payment(int amount) {
    MoneyType moneyType = MoneyType.getType( amount );
    if (moneyType == null) {
      throw new IllegalArgumentException( "Illegal Money." );
    }
    this.moneyType = moneyType;
    this.paidDatetime = LocalDateTime.now();
  }

  /**
   * 支払い金額を取得.
   *
   * @return 支払い金額
   */
  int getAmount() {
    return moneyType.getValue();
  }
}
