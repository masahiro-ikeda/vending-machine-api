package api.domain.model.cash.stock;

import api.domain.model.cash.inout.CashInout;
import api.domain.model.common.YenCurrency;

import java.util.List;

/**
 * 貨幣の所持数を保持する
 */
public class CashStock {

  // 貨幣
  private final YenCurrency yenCurrency;
  // 保持数
  private CashQuantity cashQuantity;
  // 保持金額
  private CashSum cashSum;

  public CashStock(YenCurrency yenCurrency, List<CashInout> cashInoutList) {
    this.yenCurrency = yenCurrency;
    // 他の貨幣の入出金が混じってないかチェック
    var hasOtherCurrency = cashInoutList.stream().anyMatch( cashInout -> cashInout.yenCurrency() != this.yenCurrency );
    if (hasOtherCurrency) {
      throw new RuntimeException( "Contain Illegal YenCurrency's Inout" );
    }
    this.cashQuantity = new CashQuantity( cashInoutList );
    this.cashSum = new CashSum( cashInoutList );
  }

  public YenCurrency yenCurrency() {
    return yenCurrency;
  }

  public CashQuantity cashQuantity() {
    return cashQuantity;
  }

  public CashSum cashSum() {
    return cashSum;
  }
}
