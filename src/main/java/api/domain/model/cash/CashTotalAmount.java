package api.domain.model.cash;

import api.domain.model.cash.inout.CashInout;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CashTotalAmount {

  private final int cashTotalAmount;

  /**
   * コンストラクタ.
   *
   * @param cashInoutList 入出金記録
   */
  public CashTotalAmount(List<CashInout> cashInoutList) {

    if (cashInoutList == null) {
      throw new IllegalArgumentException( "Cannot Sum Cause CashInout Being Null." );
    }

    // 入出金記録が未作成のとき
    if (cashInoutList.size() == 0) {
      this.cashTotalAmount = 0;
      return;
    }

    // 保持残高の計算 TODO ロジックがちょいと煩雑
    var sortedList = cashInoutList.stream().sorted( Comparator.comparing( CashInout::cashInoutAt ) ).collect( Collectors.toList() );
    this.cashTotalAmount = sortedList.stream().mapToInt( cashInout -> {
      switch (cashInout.cashInoutType()) {
        case IN:
          return cashInout.yenCurrency().value() * cashInout.cashInoutQuantity().value();
        case OUT:
          return cashInout.yenCurrency().value() * cashInout.cashInoutQuantity().value() * -1;
        default:
          throw new RuntimeException( "CashInoutTypeの未実装選択肢あり" );
      }
    } ).sum();
  }

  /**
   * @return 保持残高の整数値.
   */
  public int value() {
    return this.cashTotalAmount;
  }
}
