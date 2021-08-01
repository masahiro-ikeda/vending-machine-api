package api.domain.model.cash;

import api.domain.model.cash.inout.CashInout;
import api.domain.model.cash.inout.CashInoutFactory;
import api.domain.model.cash.stock.CashQuantity;
import api.domain.model.cash.stock.CashStock;
import api.domain.model.common.YenCurrency;
import api.domain.model.payments.ReturnAmount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 貨幣保有数を管理する集約.
 */
public class CashStocks {

  // 入出金記録一覧
  private final List<CashInout> cashInoutList;

  /**
   * コンストラクタ
   *
   * @param cashInoutList 現金一覧
   */
  public CashStocks(List<CashInout> cashInoutList) {
    if (cashInoutList == null) {
      throw new IllegalArgumentException( "CashInoutList Not Permit Null." );
    }
    this.cashInoutList = cashInoutList;
  }

  /**
   * 保持している現金の枚数を数える.
   */
  private List<CashStock> cashStockList() {
    List<CashStock> cashStockList = new ArrayList<>();

    // 入出金がない場合は何もしない
    if (this.cashInoutList.size() == 0) {
      return cashStockList;
    }

    var currencyList = YenCurrency.values();
    for (YenCurrency yenCurrency : currencyList) {
      var cashInoutByCurrency = cashInoutList.stream().filter( e -> e.yenCurrency() == yenCurrency ).collect( Collectors.toList() );
      CashStock cashStockByCurrency = new CashStock( yenCurrency, cashInoutByCurrency );
      cashStockList.add( cashStockByCurrency );
    }

    return cashStockList;
  }

  /**
   * 支払い時に現金を増やす.
   *
   * @param paymentYenCurrency 支払われたお金
   */
  public void inCash(YenCurrency paymentYenCurrency) {
    var paymentCashInout = CashInoutFactory.newIn( paymentYenCurrency );
    cashInoutList.add( paymentCashInout );
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

    // 金額が大きい順にソート
    var sortedCashStockListByCurrencyDesc = cashStockList().stream().sorted(
        (s1, s2) -> s2.yenCurrency().value() - s1.yenCurrency().value() ).collect( Collectors.toList() );

    // 出金済金額
    int outAmount = 0;
    for (CashStock cashStock : sortedCashStockListByCurrencyDesc) {
      // 未出金残額
      int leftAmount = returnAmount.value() - outAmount;

      // 残額が貨幣の金額以下の場合は出金できない
      if (leftAmount < cashStock.yenCurrency().value()) {
        continue;
      }

      // 貨幣ごとの取り出し枚数を算出
      CashQuantity outQuantity;
      if (leftAmount >= cashStock.cashSum().value()) {
        outQuantity = new CashQuantity( cashStock.cashQuantity().value() );
      } else {
        outQuantity = new CashQuantity( leftAmount / cashStock.yenCurrency().value() );
      }

      // 入出金記録を作成
      var cashOut = CashInoutFactory.newOut( cashStock.yenCurrency(), outQuantity );
      this.cashInoutList.add( cashOut );

      // 出金残額に反映
      outAmount += cashStock.yenCurrency().value() * outQuantity.value();
    }

    // 出金しきれるかチェック
    if (returnAmount.value() - outAmount > 0) {
      throw new RuntimeException( "Cannot Out Cash Cause Not Enough Cash." );
    }
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
    return new CashTotalAmount( cashStockList() );
  }
}
