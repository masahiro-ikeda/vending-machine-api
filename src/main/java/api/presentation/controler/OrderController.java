package api.presentation.controler;

import api.application.DisplayUseCase;
import api.application.PaymentUseCase;
import api.application.PurchaseUseCase;
import api.application.RepaymentUseCase;
import api.presentation.controler.form.PaymentForm;
import api.presentation.controler.form.PurchaseForm;
import api.presentation.viewmodel.DrinkViewModel;
import api.presentation.viewmodel.PurchaseViewModel;
import api.presentation.viewmodel.RepaymentViewModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin // TODO Webサーバー側に移植
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

  @GetMapping("drinks")
  public List<DrinkViewModel> searchDrinks() {
    return displayUseCase.searchDrinks();
  }

  @GetMapping("paymentAmount")
  public int fetchPaymentAmount() {
    return displayUseCase.fetchPaymentAmount();
  }

  @PostMapping("pay")
  public int pay(@Validated @RequestBody PaymentForm form) {
    return paymentUseCase.pay( form.getAmount() );
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
