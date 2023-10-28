package api.presentation.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class PaymentForm {

  @NotNull
  @Positive
  private Integer paymentAmount;
}
