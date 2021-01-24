package api.domain.entity.drink;

import api.domain.valueobject.Name;
import api.domain.valueobject.Price;
import api.domain.valueobject.Quantity;
import api.domain.valueobject.TemperatureState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Drink {
  private final int drinkId;
  private final Name drinkName;
  private final Price drinkPrice;
  private final TemperatureState temperatureState;
  private Quantity drinkQuantity;

  /**
   * 購入可能か？
   *
   * @param amount 金額
   * @return 購入可能な場合true
   */
  public boolean isSaleable(int amount) {

    // 価格チェック
    if (amount < drinkPrice.intValue()) {
      return false;
    }

    // 在庫チェック
    return drinkQuantity.intValue() > 0;
  }

  /**
   * 在庫の出荷（売れた）.
   */
  public void ship() {
    drinkQuantity.decrease( 1 );
  }
}
