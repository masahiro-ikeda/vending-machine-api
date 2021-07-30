package api.domain.model.drink;

import api.domain.model.drink.inout.DrinkInout;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数量を表す値オブジェクト.
 */
public class DrinkQuantity {

  // 数量
  private final int drinkQuantity;

  /**
   * コンストラクタ.
   *
   * @param drinkInoutList ドリンク入出庫記録
   */
  public DrinkQuantity(List<DrinkInout> drinkInoutList) {

    if (drinkInoutList == null) {
      throw new IllegalArgumentException( "Cannot Sum Cause DrinkInout Being Null." );
    }

    // 入出庫記録が未作成のとき
    if (drinkInoutList.size() == 0) {
      this.drinkQuantity = 0;
      return;
    }

    // 在庫数の計算
    var sortedList = drinkInoutList.stream().sorted( Comparator.comparing( DrinkInout::drinkInoutAt ) ).collect( Collectors.toList() );
    this.drinkQuantity = sortedList.stream().mapToInt( drinkInout -> {
      switch (drinkInout.drinkInoutType()) {
        case IN:
          return drinkInout.drinkInoutQuantity().value();
        case OUT:
          return drinkInout.drinkInoutQuantity().value() * -1;
        default:
          throw new RuntimeException( "DrinkInoutTypeの未実装選択肢あり" );
      }
    } ).sum();
  }

  /**
   * コンストラクタ.
   *
   * @param drinkQuantity 数量値
   */
  public DrinkQuantity(int drinkQuantity) {
    if (drinkQuantity < 0) {
      throw new IllegalArgumentException( "Quantity Not Permit Minus." );
    }
    this.drinkQuantity = drinkQuantity;
  }

  /**
   * 数量の値をintで返す.
   *
   * @return 数量
   */
  public int value() {
    return drinkQuantity;
  }

  /**
   * 購入できる在庫があるか？
   *
   * @param orderDrinkQuantity
   * @return 購入可在庫あり=true
   */
  public boolean canShip(DrinkQuantity orderDrinkQuantity) {
    return this.drinkQuantity > orderDrinkQuantity.value();
  }
}
