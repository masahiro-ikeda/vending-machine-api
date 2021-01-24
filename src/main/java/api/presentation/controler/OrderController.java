package api.presentation.controler;

import api.presentation.controler.form.PaymentForm;
import api.presentation.controler.form.PurchaseForm;
import api.presentation.viewmodel.PurchaseViewModel;
import api.presentation.viewmodel.RepaymentViewModel;
import api.presentation.viewmodel.VendingMachineViewModel;
import api.application.DisplayUseCase;
import api.application.PaymentUseCase;
import api.application.PurchaseUseCase;
import api.application.RepaymentUseCase;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

  private PaymentUseCase paymentUseCase;
  private PurchaseUseCase purchaseUseCase;
  private RepaymentUseCase repaymentUseCase;
  private DisplayUseCase displayUseCase;

  public OrderController(PaymentUseCase paymentUseCase, PurchaseUseCase purchaseUseCase, RepaymentUseCase repaymentUseCase, DisplayUseCase displayUseCase) {
    this.paymentUseCase = paymentUseCase;
    this.purchaseUseCase = purchaseUseCase;
    this.repaymentUseCase = repaymentUseCase;
    this.displayUseCase = displayUseCase;
  }

  @GetMapping
  public VendingMachineViewModel search() {
    return displayUseCase.searchDrinkDisplays();
  }

  @PostMapping("pay")
  public void pay(@Validated @RequestBody PaymentForm form) {
    paymentUseCase.pay( form.getAmount() );
  }

  @PostMapping("buy")
  public PurchaseViewModel buy(@Validated @RequestBody PurchaseForm form) {
    return purchaseUseCase.buy( form.getDrinkId() );
  }

  @PostMapping("repay")
  public RepaymentViewModel repay() {
    return repaymentUseCase.repay();
  }
}
