package api.domain.cash;

import api.domain.cash.inout.CashInout;
import api.domain.YenCurrency;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 貨幣の所持数を保持する
 */
public class CashStock {

  // 貨幣
  private final YenCurrency yenCurrency;
  // 保持数
  private int cashQuantity;

  public CashStock(YenCurrency yenCurrency, List<CashInout> cashInouts) {
    this.yenCurrency = yenCurrency;

    // 保有枚数計算
    List<CashInout> targetInout = cashInouts.stream().filter(inout -> yenCurrency.equals(inout.yenCurrency())).collect(Collectors.toList());
    for (CashInout inout : targetInout) {
      switch (inout.cashInoutType()) {
        case IN:
          cashQuantity += inout.cashInoutQuantity();
          break;
        case OUT:
          cashQuantity -= inout.cashInoutQuantity();
          break;
        default:
          throw new IllegalStateException();
      }
    }
  }

  public YenCurrency yenCurrency() {
    return yenCurrency;
  }

  public int cashQuantity() {
    return cashQuantity;
  }

  public int sum() {
    return yenCurrency.value() * cashQuantity;
  }
}
