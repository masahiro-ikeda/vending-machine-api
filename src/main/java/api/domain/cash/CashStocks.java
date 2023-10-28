package api.domain.cash;

import api.domain.cash.inout.CashInout;
import api.domain.cash.inout.CashInoutType;
import api.domain.YenCurrency;

import java.time.LocalDateTime;
import java.util.*;
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
      throw new IllegalArgumentException("CashInoutList Not Permit Null.");
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
      var cashInoutByCurrency = cashInoutList.stream().filter(e -> e.yenCurrency() == yenCurrency).collect(Collectors.toList());
      CashStock cashStockByCurrency = new CashStock(yenCurrency, cashInoutByCurrency);
      cashStockList.add(cashStockByCurrency);
    }

    return cashStockList;
  }

  /**
   * 入金.
   */
  public void inCash(YenCurrency paymentYenCurrency) {
    CashInout cashIn = new CashInout(
        UUID.randomUUID().toString(),
        paymentYenCurrency,
        CashInoutType.IN,
        1,
        LocalDateTime.now());
    cashInoutList.add(cashIn);
  }

  /**
   * 出金.
   */
  public void outCash(YenCurrency paymentYenCurrency, int cashQuantity) {
    CashInout cashOut = new CashInout(
        UUID.randomUUID().toString(),
        paymentYenCurrency,
        CashInoutType.OUT,
        cashQuantity,
        LocalDateTime.now());
    cashInoutList.add(cashOut);
  }

  /**
   * 返金する.
   */
  public Map<Integer, Integer> repay(int repaymentAmount) {

    // 返金一覧
    Map<Integer, Integer> outStocks = new HashMap<>();
    Arrays.stream(YenCurrency.values()).forEach(c -> outStocks.put(c.value(), 0));

    // 金額が大きい順にソート
    var sortedCashStockListByCurrencyDesc = cashStockList().stream().sorted(
        (s1, s2) -> s2.yenCurrency().value() - s1.yenCurrency().value()).collect(Collectors.toList());

    int outAmount = 0;// 出金済金額
    for (CashStock cashStock : sortedCashStockListByCurrencyDesc) {
      // 未出金残額
      int leftAmount = repaymentAmount - outAmount;

      // 残額が貨幣の金額以下の場合は出金できない
      if (leftAmount < cashStock.yenCurrency().value()) {
        continue;
      }

      // 貨幣ごとの取り出し枚数を計算
      int outQuantity = 0;
      if (leftAmount >= cashStock.cashSum()) {
        outQuantity += cashStock.cashQuantity();
      } else {
        outQuantity = leftAmount / cashStock.yenCurrency().value();
      }

      // 入出金記録を作成
      outCash(cashStock.yenCurrency(), outQuantity);
      // 出金済金額に反映
      outAmount += cashStock.yenCurrency().value() * outQuantity;

      // 出金一覧
      outStocks.put(cashStock.yenCurrency().value(), outQuantity);
    }

    // 出金しきれるかチェック
    if (repaymentAmount - outAmount > 0) {
      throw new RuntimeException("Cannot Out Cash Cause Not Enough Cash.");
    }

    return outStocks;
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
  public int cashTotalAmount() {
    // 保有する貨幣がない場合
    if (cashStockList().size() == 0) {
      return 0;
    }

    return cashStockList().stream().mapToInt(cashStock -> cashStock.cashSum()).sum();
  }
}
