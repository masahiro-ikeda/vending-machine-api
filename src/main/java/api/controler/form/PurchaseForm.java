package api.controler.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PurchaseForm {

  @NotNull
  private int drinkId;
}
