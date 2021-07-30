package api.presentation.controler;

import api.application.usecase.PurchaseUseCase;
import api.presentation.controler.form.PurchaseForm;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class PurchaseController {


  private final PurchaseUseCase purchaseUseCase;

  public PurchaseController(PurchaseUseCase purchaseUseCase) {
    this.purchaseUseCase = purchaseUseCase;
  }

  @PostMapping("buy")
  public void buy(@Validated @RequestBody PurchaseForm form) {
    purchaseUseCase.purchase( form.getDrinkId() );
  }
}
