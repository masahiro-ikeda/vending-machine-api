package api.domain.entity.payment;

import api.domain.valueobject.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * お金の投入記録.
 */
@AllArgsConstructor
@Getter
public class Payment {

  private final Money money;
  private final LocalDateTime paidDatetime;
}
