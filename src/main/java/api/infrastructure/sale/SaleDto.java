package api.infrastructure.sale;

import api.domain.sale.Sale;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 販売記録.
 */
@Entity
@Table(name = "sale")
@NoArgsConstructor // JPA利用にデフォルトコンストラクタが必須なため
public class SaleDto {

  @Id
  private String saleId;
  private String drinkId;
  private int saleQuantity;
  private String paymentId;
  private String drinkInoutId;
  private LocalDateTime soldAt;

  /**
   * コンストラクタ.
   */
  public SaleDto(Sale sale) {
    this.saleId = sale.saleId();
    this.drinkId = sale.drinkId();
    this.saleQuantity = sale.saleQuantity();
    this.paymentId = sale.paymentId();
    this.drinkInoutId = sale.drinkInoutId();
    this.soldAt = sale.soldAt();
  }
}
