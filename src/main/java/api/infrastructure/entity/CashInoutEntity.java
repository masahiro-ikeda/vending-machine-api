package api.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "cash_inout")
@AllArgsConstructor
@NoArgsConstructor // JPA利用にデフォルトコンストラクタが必須なため
@Getter
public class CashInoutEntity {
  @Id
  private String cashInoutId;
  private int cashAmount;
  private String cashInoutType;
  private int cashInoutQuantity;
  private LocalDateTime cashInoutAt;
}
