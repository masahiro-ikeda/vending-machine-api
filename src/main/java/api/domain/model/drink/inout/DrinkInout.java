package api.domain.model.drink.inout;

import api.domain.model.drink.DrinkId;

import java.time.LocalDateTime;

public class DrinkInout {
  private final DrinkInoutId drinkInoutId;
  private final DrinkId drinkId;
  private final DrinkInoutType drinkInoutType;
  private final DrinkInoutQuantity drinkInoutQuantity;
  private final LocalDateTime drinkInoutAt;

  /**
   * コンストラクタ.
   *
   * @param drinkInoutId
   * @param drinkId
   * @param drinkInoutType
   * @param drinkInoutQuantity
   * @param drinkInoutAt
   */
  public DrinkInout(DrinkInoutId drinkInoutId, DrinkId drinkId, DrinkInoutType drinkInoutType, DrinkInoutQuantity drinkInoutQuantity, LocalDateTime drinkInoutAt) {
    this.drinkInoutId = drinkInoutId;
    this.drinkId = drinkId;
    this.drinkInoutType = drinkInoutType;
    this.drinkInoutQuantity = drinkInoutQuantity;
    this.drinkInoutAt = drinkInoutAt;
  }

  public DrinkInoutId drinkInoutId() {
    return drinkInoutId;
  }

  public DrinkId drinkId() {
    return drinkId;
  }

  public DrinkInoutType drinkInoutType() {
    return drinkInoutType;
  }

  public DrinkInoutQuantity drinkInoutQuantity() {
    return drinkInoutQuantity;
  }

  public LocalDateTime drinkInoutAt() {
    return drinkInoutAt;
  }
}
