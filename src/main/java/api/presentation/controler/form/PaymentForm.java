package api.presentation.controler.form;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class PaymentForm {

  @Positive
  private Integer amount;
}
