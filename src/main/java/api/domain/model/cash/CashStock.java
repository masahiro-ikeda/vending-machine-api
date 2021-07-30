package api.domain.model.cash;

import api.domain.model.common.YenCurrency;
import api.domain.model.cash.inout.CashInout;
import api.domain.model.cash.inout.CashInoutFactory;
import api.domain.model.cash.inout.CashInoutType;
import api.domain.model.payments.ReturnAmount;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 現金残枚数クラス（集約）.
 */
public class CashStock {

  // 保有する現金一覧
  private final List<CashInout> cashInoutList;
  private final Map<YenCurrency, CashQuantity> cashQuantityMap;
  private CashTotalAmount cashTotalAmount;

  /**
   * コンストラクタ
   *
   * @param cashInoutList 現金一覧
   */
  public CashStock(List<CashInout> cashInoutList) {
    if (cashInoutList == null) {
      throw new IllegalArgumentException( "CashInoutList Not Permit Null." );
    }
    this.cashInoutList = cashInoutList;

    // 保有残高の計算
    this.cashTotalAmount = new CashTotalAmount( this.cashInoutList );

    // 保有枚数の計算
    cashQuantityMap = new HashMap<>();
    countCashQuantity();
  }

  /**
   * 保持している現金の枚数を数える.
   */
  private void countCashQuantity() {
    // 入出金がない場合は何もしない
    if (this.cashInoutList.size() == 0) {
      return;
    }

    var currencyList = YenCurrency.values();
    var sortedList = cashInoutList.stream().sorted( Comparator.comparing( CashInout::cashInoutAt ) ).collect( Collectors.toList() );
    for (YenCurrency yenCurrency : currencyList) {
      CashQuantity cashQuantity = new CashQuantity( 0 );
      // 該当するお金の枚数を数える
      for (CashInout inout : sortedList) {
        // TODO ここのロジックはスッキリさせたい
        if (yenCurrency == inout.yenCurrency()) {
          if (inout.cashInoutType() == CashInoutType.IN) {
            cashQuantity = new CashQuantity( cashQuantity.value() + inout.cashInoutQuantity().value() );
          }
          if (inout.cashInoutType() == CashInoutType.OUT) {
            cashQuantity = new CashQuantity( cashQuantity.value() - inout.cashInoutQuantity().value() );
          }
        }
      }
      cashQuantityMap.put( yenCurrency, cashQuantity );
    }
  }

  /**
   * 支払い時に現金を増やす.
   *
   * @param paymentYenCurrency 支払われたお金
   */
  public void inCash(YenCurrency paymentYenCurrency) {
    var paymentCashInout = CashInoutFactory.newIn( paymentYenCurrency );
    cashInoutList.add( paymentCashInout );

    // 保有残高の再計算
    this.cashTotalAmount = new CashTotalAmount( this.cashInoutList );

    // 保有枚数の再計算
    countCashQuantity();
  }

  /**
   * 指定金額分の現金を出金する.
   * TODO 要クラス再設計
   *
   * @param returnAmount 取り出す金額
   */
  public void outCash(ReturnAmount returnAmount) {
    List<CashInout> outCashInoutList = new ArrayList<>();

    var yenCurrencyList = Arrays.asList( YenCurrency.values() );
    Collections.reverse( yenCurrencyList );

    // 出金済金額
    int outAmount = 0;
    for (YenCurrency yenCurrency : yenCurrencyList) {
      // 未出金残額
      int leftAmount = returnAmount.value() - outAmount;

      // 残額が貨幣の金額以下の場合は出金できない
      if (leftAmount < yenCurrency.value()) {
        continue;
      }

      // 貨幣ごとの合計保持金額
      var quantity = cashQuantityMap.get( yenCurrency );
      int cashAmount = yenCurrency.value() * quantity.value();

      // 貨幣ごとの取り出し枚数を算出
      CashQuantity outQuantity;
      if (leftAmount >= cashAmount) {
        outQuantity = new CashQuantity( quantity.value() );
      } else {
        outQuantity = new CashQuantity( leftAmount / yenCurrency.value() );
      }

      // 入出金記録を作成
      CashInout out = CashInoutFactory.newOut( yenCurrency, outQuantity );
      this.cashInoutList.add( out );

      // 出金残額に反映
      outAmount += yenCurrency.value() * outQuantity.value();
    }

    // 出金しきれない場合は異常終了
    if (returnAmount.value() - outAmount != 0) {
      throw new RuntimeException( "Illegal ReturnAmount." );
    }

    // 保有残高の再計算
    this.cashTotalAmount = new CashTotalAmount( this.cashInoutList );

    // 保有枚数の再計算
    countCashQuantity();
  }

  /**
   * @return 保持している入出金記録一覧
   */
  public List<CashInout> cashInoutList() {
    return this.cashInoutList;
  }

  /**
   * @return 保持している現金の合計残高
   */
  public CashTotalAmount cashTotalAmount() {
    return this.cashTotalAmount;
  }
}
