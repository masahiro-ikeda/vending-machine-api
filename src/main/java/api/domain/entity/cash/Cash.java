package api.domain.entity.cash;

import api.domain.model.payment.YenCurrency;
import api.domain.valueobject.Quantity;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 現金モデル.
 * 貨幣の種類と数量を保持
 */
@AllArgsConstructor
@Getter
public class Cash {

  // 貨幣
  private final YenCurrency yenCurrency;
  // 保持数
  private Quantity cashQuantity;

  /**
   * 保持数を増やす.
   *
   * @param increaseQuantity 増分
   */
  void add(int increaseQuantity) {
    cashQuantity = cashQuantity.increase( increaseQuantity );
  }

  /**
   * 取り出す.
   *
   * @param takeQuantity 取り出し分
   * @return 取り出した現金
   */
  Cash take(int takeQuantity) {
    cashQuantity = cashQuantity.decrease( takeQuantity );
    return new Cash(yenCurrency, new Quantity( takeQuantity ) );
  }

  /**
   * 合計残高を取得.
   *
   * @return 合計残高
   */
  int amount() {
    return yenCurrency.value() * cashQuantity.intValue();
  }
}
