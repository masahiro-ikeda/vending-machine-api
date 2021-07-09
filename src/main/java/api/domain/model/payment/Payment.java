package api.domain.model.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 支払い.
 */
@AllArgsConstructor
@Getter
public class Payment {
  private final YenCurrency yenCurrency;
  private final LocalDateTime paidDatetime;
}
