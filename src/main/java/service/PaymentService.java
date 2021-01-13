package service;

import model.Coin;
import model.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentService {

  private Payment payment;

  public PaymentService() {
    List<Coin> paid = new ArrayList<>();
    payment = new Payment(paid);
  }

  /**
   * お金を入れる.
   *
   * @param amount 投入した金額
   */
  public void doPayment(int amount) {

    // お金を投入
    payment.pay( amount );
    System.out.println("合計金額は" + payment.getSumPayment() + "円です。");

    // TODO 投入金額で購入可能なドリンクのリストを取得
  }
}
