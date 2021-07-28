package api.infrastructure.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor // JPA利用にデフォルトコンストラクタが必須なため
@Getter
public class PaymentEntity {
  @Id
  private String paymentId;
  private int paymentAmount;
  private String paymentType;
  private LocalDateTime paymentAt;
}
