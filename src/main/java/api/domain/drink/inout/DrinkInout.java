package api.domain.drink.inout;

import java.time.LocalDateTime;

public class DrinkInout {
  private final String drinkInoutId;
  private final String drinkId;
  private final DrinkInoutType drinkInoutType;
  private final int drinkInoutQuantity;
  private final LocalDateTime drinkInoutAt;

  /**
   * コンストラクタ.
   */
  public DrinkInout(String drinkInoutId, String drinkId, DrinkInoutType drinkInoutType, int drinkInoutQuantity, LocalDateTime drinkInoutAt) {
    this.drinkInoutId = drinkInoutId;
    this.drinkId = drinkId;
    this.drinkInoutType = drinkInoutType;
    this.drinkInoutQuantity = drinkInoutQuantity;
    this.drinkInoutAt = drinkInoutAt;
  }

  public String drinkInoutId() {
    return drinkInoutId;
  }

  public String drinkId() {
    return drinkId;
  }

  public DrinkInoutType drinkInoutType() {
    return drinkInoutType;
  }

  public int drinkInoutQuantity() {
    return drinkInoutQuantity;
  }

  public LocalDateTime drinkInoutAt() {
    return drinkInoutAt;
  }
}
