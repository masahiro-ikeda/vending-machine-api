package api.presentation.controler;

import api.application.sale.SaleInput;
import api.application.sale.SaleOutput;
import api.application.sale.SaleUseCase;
import api.presentation.form.SaleForm;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SaleController {

  private final SaleUseCase saleUseCase;

  SaleController(SaleUseCase saleUseCase) {
    this.saleUseCase = saleUseCase;
  }

  @PostMapping("api/sale")
  public SaleOutput sale(@Validated @RequestBody SaleForm form) {
    SaleInput inputData = new SaleInput(form.getDrinkId());
    return saleUseCase.execute(inputData);
  }
}
