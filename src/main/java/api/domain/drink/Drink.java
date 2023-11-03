package api.domain.drink;

import api.domain.drink.inout.DrinkInout;
import api.domain.drink.inout.DrinkInoutType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Drink {
  private final String drinkId;
  private final int drinkPrice;
  private final List<DrinkInout> drinkInoutList;

  /**
   * コンストラクタ.
   */
  public Drink(String drinkId, int drinkPrice, List<DrinkInout> drinkInoutList) {
    this.drinkId = drinkId;
    this.drinkPrice = drinkPrice;
    this.drinkInoutList = drinkInoutList;
  }

  /**
   * 購入可能かチェック.
   */
  public void validateOnSale(int saleQuantity, int totalPaymentAmount) {

    // 購入可能な在庫はあるか？
    if (saleQuantity > drinkQuantity()) {
      throw new RuntimeException("Not Enough Stock.");
    }
    // 支払金額は足りているか？
    if (drinkPrice > totalPaymentAmount) {
      throw new RuntimeException("Not Enough Payment.");
    }
  }

  /**
   * 出庫.
   */
  public String out(int outQuantity) {
    String drinkInoutId = UUID.randomUUID().toString();
    DrinkInout drinkOut = new DrinkInout(
        drinkInoutId,
        drinkId,
        DrinkInoutType.OUT,
        outQuantity,
        LocalDateTime.now()
    );
    drinkInoutList.add(drinkOut);
    return drinkInoutId;
  }

  /**
   * ドリンクID.
   */
  public String drinkId() {
    return this.drinkId;
  }

  /**
   * ドリンクの数量.
   */
  public int drinkQuantity() {

    int quantity = 0;
    for (DrinkInout inout : drinkInoutList) {
      switch (inout.drinkInoutType()) {
        case IN:
          quantity += inout.drinkInoutQuantity();
          break;
        case OUT:
          quantity -= inout.drinkInoutQuantity();
          break;
        default:
          throw new IllegalStateException();
      }
    }
    return quantity;
  }

  /**
   * ドリンクの価格.
   *
   * @return
   */
  public int drinkPrice() {
    return this.drinkPrice;
  }

  /**
   * ドリンクの入出庫リスト.
   *
   * @return
   */
  public List<DrinkInout> drinkInoutList() {
    return drinkInoutList;
  }
}
