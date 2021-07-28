package api.domain.model.cash;

import api.domain.valueobject.Quantity;
import api.domain.model.payment.YenCurrency;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 現金モデル.
 * 貨幣の種類と数量を保持
 */
@AllArgsConstructor
@Getter
public class CashStock {

  // 貨幣
  private final YenCurrency yenCurrency;
  // 保持数
  private Quantity cashQuantity;

  /**
   * 保持数を増やす.
   *
   * @param increased 増分
   */
  void add(Quantity increased) {
    cashQuantity = cashQuantity.increase( increased );
  }

  /**
   * 取り出す.
   *
   * @param token 取り出し分
   * @return 取り出した現金
   */
  CashStock take(Quantity token) {
    cashQuantity = cashQuantity.decrease( token );
    return new CashStock( yenCurrency, new Quantity( token.value() ) );
  }

  /**
   * 合計残高を取得.
   *
   * @return 合計残高
   */
  int sum() {
    return yenCurrency.value() * cashQuantity.value();
  }
}
