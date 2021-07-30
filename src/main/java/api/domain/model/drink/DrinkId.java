package api.domain.model.drink;

import java.util.UUID;

public class DrinkId {
  private final UUID drinkId;

  /**
   * 新規生成.
   */
  public DrinkId() {
    this.drinkId = UUID.randomUUID();
  }

  /**
   * 復元.
   *
   * @param drinkId 文字列のUUID
   */
  public DrinkId(String drinkId) {
    this.drinkId = UUID.fromString( drinkId );
  }

  /**
   * @return 文字列の値
   */
  public String value() {
    return this.drinkId.toString();
  }
}
