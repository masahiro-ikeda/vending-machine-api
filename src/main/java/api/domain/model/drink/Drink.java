package api.domain.model.drink;

import api.domain.model.drink.inout.DrinkInout;
import api.domain.model.drink.inout.DrinkInoutId;
import api.domain.model.drink.inout.DrinkInoutQuantity;
import api.domain.model.drink.inout.DrinkInoutType;

import java.time.LocalDateTime;
import java.util.List;

public class Drink {
  private final DrinkId drinkId;
  private final DrinkPrice drinkPrice;
  private DrinkQuantity drinkQuantity;
  private final List<DrinkInout> drinkInoutList;

  /**
   * コンストラクタ.
   *
   * @param drinkId
   * @param drinkPrice
   * @param drinkInoutList
   */
  public Drink(DrinkId drinkId, DrinkPrice drinkPrice, List<DrinkInout> drinkInoutList) {
    this.drinkId = drinkId;
    this.drinkPrice = drinkPrice;
    this.drinkInoutList = drinkInoutList;
    this.drinkQuantity = new DrinkQuantity( this.drinkInoutList );
  }

  /**
   * 出庫
   *
   * @param outQuantity
   */
  public void out(DrinkQuantity outQuantity) {
    DrinkInout drinkOut = new DrinkInout(
        new DrinkInoutId(),
        this.drinkId,
        DrinkInoutType.OUT,
        new DrinkInoutQuantity( outQuantity.value() ),
        LocalDateTime.now()
    );
    drinkInoutList.add( drinkOut );

    // 在庫数の再計算
    this.drinkQuantity = new DrinkQuantity( this.drinkInoutList );
  }

  /**
   * ドリンクID.
   *
   * @return
   */
  public DrinkId drinkId() {
    return this.drinkId;
  }

  /**
   * ドリンクの数量.
   *
   * @return
   */
  public DrinkQuantity drinkQuantity() {
    return this.drinkQuantity;
  }

  /**
   * ドリンクの価格.
   *
   * @return
   */
  public DrinkPrice drinkPrice() {
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
