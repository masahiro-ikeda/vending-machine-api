package model;

import java.util.List;

/**
 * 支払いを管理するクラス
 */
public class Payment {

  private List<Coin> paid;

  public Payment(List<Coin> paid) {
    this.paid = paid;
  }

  /**
   * 支払い.
   *
   * @param amount 支払い額
   */
  public void pay(int amount) {
    Coin coin = new Coin( amount );
    paid.add( coin );
  }

  /**
   * 支払い済み金額の合計.
   *
   * @return 支払い済み金額合計
   */
  public int getSumPayment() {
    return paid.stream().mapToInt( Coin::getAmount ).sum();
  }
}
