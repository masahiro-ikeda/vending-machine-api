package api.domain.model.drink.inout;

import java.util.UUID;

public class DrinkInoutId {
  private final UUID drinkInoutId;

  /**
   * 新規生成.
   */
  public DrinkInoutId() {
    this.drinkInoutId = UUID.randomUUID();
  }

  /**
   * 復元.
   *
   * @param drinkInoutId 文字列のUUID
   */
  public DrinkInoutId(String drinkInoutId) {
    this.drinkInoutId = UUID.fromString( drinkInoutId );
  }

  /**
   * @return 文字列の値
   */
  public String value() {
    return this.drinkInoutId.toString();
  }
}
