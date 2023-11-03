package api.domain.sale;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 販売記録.
 */
public class Sale {
  private final String saleId;
  private final String drinkId;
  private final int saleQuantity;
  private final String paymentId;
  private final String drinkInoutId;
  private final LocalDateTime soldAt;

  public Sale(String drinkId, int saleQuantity, String paymentId, String drinkInoutId) {
    this.saleId = UUID.randomUUID().toString();
    this.drinkId = drinkId;
    this.saleQuantity = saleQuantity;
    this.paymentId = paymentId;
    this.drinkInoutId = drinkInoutId;
    this.soldAt = LocalDateTime.now();
  }

  public String saleId() {
    return saleId;
  }

  public String drinkId() {
    return drinkId;
  }

  public int saleQuantity() {
    return saleQuantity;
  }

  public String paymentId() {
    return paymentId;
  }

  public String drinkInoutId() {
    return drinkInoutId;
  }

  public LocalDateTime soldAt() {
    return soldAt;
  }
}
