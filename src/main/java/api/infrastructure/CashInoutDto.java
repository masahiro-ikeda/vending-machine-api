package api.infrastructure;

import api.domain.cash.inout.CashInout;
import api.domain.cash.inout.CashInoutType;
import api.domain.YenCurrency;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "cash_inout")
@NoArgsConstructor // JPA利用にデフォルトコンストラクタが必須なため
public class CashInoutDto {

  @Id
  private String cashInoutId;
  private int cashCurrency;
  private String cashInoutType;
  private int cashInoutQuantity;
  private LocalDateTime cashInoutAt;

  /**
   * コンストラクタ.
   */
  public CashInoutDto(CashInout cashInout) {
    this.cashInoutId = cashInout.cashInoutId();
    this.cashCurrency = cashInout.yenCurrency().value();
    this.cashInoutType = cashInout.cashInoutType().name();
    this.cashInoutQuantity = cashInout.cashInoutQuantity();
    this.cashInoutAt = cashInout.cashInoutAt();
  }

  /**
   * 復元.
   */
  CashInout toEntity() {
    return new CashInout(
        this.cashInoutId,
        YenCurrency.of(this.cashCurrency),
        CashInoutType.valueOf(this.cashInoutType),
        this.cashInoutQuantity,
        this.cashInoutAt
    );
  }
}
