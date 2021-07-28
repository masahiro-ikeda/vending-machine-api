package api.domain.model.cash;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import api.domain.valueobject.Quantity;
import api.domain.model.payment.Payment;
import api.domain.model.payment.YenCurrency;
import lombok.Getter;

/**
 * 現金管理クラス（集約）.
 */
@Getter
public class CashStocks {

  // 保有する現金一覧
  private List<CashStock> cashStockList;

  /**
   * コンストラクタ
   *
   * @param cashStockList 現金一覧
   */
  public CashStocks(List<CashStock> cashStockList) {
    this.cashStockList = cashStockList;
    // 常に金額が大きい順にソート
    // TODO 取り出し用途に応じたソート順が使えるようにする
    cashStockList.sort( (c1, c2) -> c2.getYenCurrency().value() - c1.getYenCurrency().value() );
  }

  /**
   * 支払い時に現金を増やす.
   *
   * @param payment 支払い
   */
  public void add(Payment payment) {
    Optional<CashStock> target = cashStockList.stream().filter( c -> c.getYenCurrency().amount().equals(payment.getPaymentAmount()) ).findFirst();
    if (target.isPresent()) {
      target.get().add( new Quantity( 1 ) );
    } else {
      // 未管理の貨幣は管理対象に追加
      cashStockList.add( new CashStock( YenCurrency.of( payment.getPaymentAmount().value() ), new Quantity( 1 ) ) );
    }
  }

  /**
   * 指定金額分の現金を取り出す.
   *
   * @param amount 取り出す金額
   * @return 取り出した現金一覧
   */
  public List<CashStock> take(int amount) {
    List<CashStock> takeCashStockList = new ArrayList<>();
    int takeAmount = 0;
    for (CashStock cashStock : cashStockList) {
      int leftAmount = amount - takeAmount;
      if (leftAmount < cashStock.getYenCurrency().value()) {
        continue;
      }
      int takeQuantity = leftAmount / cashStock.getYenCurrency().value();
      CashStock takeCashStock = cashStock.take( new Quantity( takeQuantity ) );

      takeCashStockList.add( takeCashStock );
      takeAmount += takeCashStock.sum();
    }
    return takeCashStockList;
  }

  /**
   * 保持している現金の残高合計を取得.
   *
   * @return 合計残高
   */
  public int getTotalAmount() {
    return cashStockList.stream().mapToInt( CashStock::sum ).sum();
  }
}
