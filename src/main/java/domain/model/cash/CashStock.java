package domain.model.cash;

import domain.model.payment.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 現金の集約.
 */
public class CashStock {

  // 保有する現金一覧
  private List<Cash> cashList;

  /**
   * コンストラクタ
   *
   * @param cashList 現金一覧
   */
  public CashStock(List<Cash> cashList) {
    this.cashList = cashList;
    // 常に金額が大きい順にソート
    // TODO 取り出し用途に応じたソート順が使えるようにする
    cashList.sort( (c1, c2) -> c2.getMoneyType().getValue() - c1.getMoneyType().getValue() );
  }

  /**
   * 支払い時に現金を増やす.
   *
   * @param payment 支払い
   */
  public void putIn(Payment payment) {
    Optional<Cash> target = cashList.stream().filter( c -> c.getMoneyType() == payment.getMoneyType() ).findFirst();
    if (target.isPresent()) {
      target.get().add( 1 );
    } else {
      // 未管理の貨幣は管理対象に追加
      cashList.add( new Cash( payment.getMoneyType().getValue(), 1 ) );
    }
  }

  /**
   * 指定金額を取り出す.
   *
   * @param amount 取り出す金額
   * @return 貨幣と枚数
   */
  public List<Cash> putOut(int amount) {

    int left = amount;

    // 取り出し可能な貨幣とその枚数を計算
    List<Cash> outs = new ArrayList<>();
    for (Cash cash : cashList) {
      if (left < cash.getMoneyType().getValue()) {
        continue;
      }
      int takeCount = left / cash.getMoneyType().getValue();
      left -= cash.getMoneyType().getValue() * takeCount;
      outs.add( cash.take( takeCount ) );
    }

    return outs;
  }

  /**
   * 保持している現金の合計残高を取得.
   *
   * @return 合計残高
   */
  public int getTotalCashAmount() {
    return cashList.stream().mapToInt( Cash::cashAmount ).sum();
  }

  public List<Cash> getCashList() {
    return cashList;
  }
}
