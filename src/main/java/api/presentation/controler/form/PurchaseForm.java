package api.presentation.controler.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PurchaseForm {

  @NotNull
  private String drinkId;
}
