package api.domain.entity.cash;

import api.domain.enumeration.MoneyType;
import lombok.Getter;

/**
 * 現金モデル.
 * 貨幣の種類と保持数を管理する
 */
@Getter
public class Cash {

  // 貨幣の種類
  private MoneyType moneyType;
  // 保持数
  private int cashQuantity;

  /**
   * コンストラクタ.
   *
   * @param amount       額面
   * @param cashQuantity 貨幣の残数
   */
  public Cash(int amount, int cashQuantity) {
    MoneyType moneyType = MoneyType.getType( amount );
    if (moneyType == null) {
      throw new IllegalArgumentException( "Illegal Money." );
    }
    this.moneyType = moneyType;
    this.cashQuantity = cashQuantity;
  }

  /**
   * 現金を増やす.
   *
   * @param count 枚数
   */
  void add(int count) {
    cashQuantity += count;
  }

  /**
   * 現金を取り出す.
   *
   * @param count 枚数
   * @return 取り出した貨幣
   */
  Cash take(int count) {
    this.cashQuantity -= count;
    return new Cash( moneyType.getValue(), count );
  }

  /**
   * 合計残高を取得.
   *
   * @return 合計残高
   */
  int cashAmount() {
    return moneyType.getValue() * cashQuantity;
  }
}
