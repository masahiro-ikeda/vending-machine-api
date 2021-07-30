package api.domain.model.cash.inout;

import api.domain.model.common.YenCurrency;
import api.domain.model.cash.CashQuantity;

import java.time.LocalDateTime;

public class CashInoutFactory {

  /**
   * 支払い時の入金記録を生成.
   *
   * @param yenCurrency
   * @return
   */
  public static CashInout newIn(YenCurrency yenCurrency) {
    return new CashInout(
        new CashInoutId(),
        yenCurrency,
        CashInoutType.IN,
        new CashInoutQuantity( 1 ),
        LocalDateTime.now()
    );
  }

  /**
   * 出金記録を生成.
   *
   * @param yenCurrency
   * @return
   */
  public static CashInout newOut(YenCurrency yenCurrency, CashQuantity cashQuantity) {
    return new CashInout(
        new CashInoutId(),
        yenCurrency,
        CashInoutType.OUT,
        new CashInoutQuantity( cashQuantity.value() ),
        LocalDateTime.now()
    );
  }

  /**
   * 復元.
   *
   * @param cashInoutId
   * @param yenCurrency
   * @param cashInoutType
   * @param cashInoutQuantity
   * @param cashInoutAt
   * @return
   */
  public static CashInout restore(String cashInoutId, int yenCurrency, String cashInoutType, int cashInoutQuantity, LocalDateTime cashInoutAt) {
    return new CashInout(
        new CashInoutId( cashInoutId ),
        YenCurrency.of( yenCurrency ),
        CashInoutType.valueOf( cashInoutType ),
        new CashInoutQuantity( cashInoutQuantity ),
        cashInoutAt
    );
  }
}
