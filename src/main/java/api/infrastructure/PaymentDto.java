package api.infrastructure;

import api.domain.payments.Payment;
import api.domain.payments.PaymentType;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@NoArgsConstructor // JPA利用にデフォルトコンストラクタが必須なため
public class PaymentDto {

  @Id
  private String paymentId;
  private int paymentAmount;
  private String paymentType;
  private LocalDateTime paymentAt;

  /**
   * コンストラクタ.
   */
  PaymentDto(Payment payment) {
    this.paymentId = payment.id();
    this.paymentAmount = payment.paymentAmount();
    this.paymentType = payment.paymentType().toString();
    this.paymentAt = payment.paymentAt();
  }

  /**
   * 復元.
   */
  Payment toEntity() {
    return new Payment(
        paymentId,
        paymentAmount,
        PaymentType.valueOf(paymentType),
        paymentAt
    );
  }
}
