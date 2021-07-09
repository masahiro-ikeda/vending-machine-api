package api.domain.entity.cash;

import api.domain.model.payment.Payment;
import api.domain.valueobject.Quantity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 現金管理クラス（集約）.
 */
@Getter
public class CashManager {

  // 保有する現金一覧
  private List<Cash> cashList;

  /**
   * コンストラクタ
   *
   * @param cashList 現金一覧
   */
  public CashManager(List<Cash> cashList) {
    this.cashList = cashList;
    // 常に金額が大きい順にソート
    // TODO 取り出し用途に応じたソート順が使えるようにする
    cashList.sort( (c1, c2) -> c2.getYenCurrency().value() - c1.getYenCurrency().value() );
  }

  /**
   * 支払い時に現金を増やす.
   *
   * @param payment 支払い
   */
  public void add(Payment payment) {
    Optional<Cash> target = cashList.stream().filter( c -> c.getYenCurrency() == payment.getYenCurrency() ).findFirst();
    if (target.isPresent()) {
      target.get().add( 1 );
    } else {
      // 未管理の貨幣は管理対象に追加
      cashList.add( new Cash( payment.getYenCurrency(), new Quantity( 1 ) ) );
    }
  }

  /**
   * 指定金額分の現金を取り出す.
   *
   * @param amount 取り出す金額
   * @return 取り出した現金一覧
   */
  public List<Cash> take(int amount) {
    List<Cash> takeCashList = new ArrayList<>();
    int takeAmount = 0;
    for (Cash cash : cashList) {
      int leftAmount = amount - takeAmount;
      if (leftAmount < cash.getYenCurrency().value()) {
        continue;
      }
      int takeQuantity = leftAmount / cash.getYenCurrency().value();
      Cash takeCash = cash.take( takeQuantity );

      takeCashList.add( takeCash );
      takeAmount += takeCash.amount();
    }
    return takeCashList;
  }

  /**
   * 保持している現金の残高合計を取得.
   *
   * @return 合計残高
   */
  public int getTotalAmount() {
    return cashList.stream().mapToInt( Cash::amount ).sum();
  }
}
