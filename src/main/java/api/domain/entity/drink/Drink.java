package api.domain.entity.drink;

import api.domain.enumeration.DrinkTemperatureType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Drink {
  private int drinkId;
  private String drinkName;
  private int drinkPrice;
  private int drinkStockQuantity;
  private DrinkTemperatureType drinkTemperatureType;

  /**
   * 購入可能か？
   *
   * @param amount 金額
   * @return 購入可能な場合true
   */
  public boolean isSaleable(int amount) {

    // 価格チェック
    if (amount < drinkPrice) {
      return false;
    }

    // 在庫チェック
    return drinkStockQuantity > 0;
  }

  /**
   * 在庫の出荷（売れた）.
   */
  public void ship() {
    if (drinkStockQuantity <= 0) {
      throw new RuntimeException( "Insufficient Stock." );
    }
    drinkStockQuantity = drinkStockQuantity - 1;
  }
}
