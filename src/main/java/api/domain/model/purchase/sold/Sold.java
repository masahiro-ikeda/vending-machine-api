package api.domain.model.purchase.sold;

import api.domain.model.drink.DrinkId;

import java.time.LocalDateTime;

/**
 * 販売記録.
 */
public class Sold {
  private final SoldId soldId;
  private final DrinkId drinkId;
  private final SoldQuantity soldQuantity;
  private final LocalDateTime soldAt;

  public Sold(DrinkId drinkId, SoldQuantity soldQuantity) {
    this.soldId = new SoldId();
    this.drinkId = drinkId;
    this.soldQuantity = soldQuantity;
    this.soldAt = LocalDateTime.now();
  }

  public SoldId soldId() {
    return soldId;
  }

  public DrinkId drinkId() {
    return drinkId;
  }

  public SoldQuantity soldQuantity() {
    return soldQuantity;
  }

  public LocalDateTime soldAt() {
    return soldAt;
  }
}
