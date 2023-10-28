package api.presentation.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SaleForm {

  @NotNull
  private String drinkId;
}
