package api.controler;

import api.controler.form.PaymentForm;
import api.controler.form.PurchaseForm;
import api.domain.entity.cash.Cash;
import api.domain.entity.drink.DrinkDisplay;
import api.usecase.DisplayUseCase;
import api.usecase.PaymentUseCase;
import api.usecase.PurchaseUseCase;
import api.usecase.RepaymentUseCase;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
  public List<DrinkDisplay> search() {
    return displayUseCase.searchDrinkDisplays();
  }

  @PostMapping("pay")
  public List<DrinkDisplay> pay(@Validated @RequestBody PaymentForm form) {
    return paymentUseCase.pay( form.getAmount() );
  }

  @PostMapping("buy")
  public List<Cash> buy(@Validated @RequestBody PurchaseForm form) {
    return purchaseUseCase.buy( form.getDrinkId() );
  }

  @PostMapping("repay")
  public List<Cash> repay() {
    return repaymentUseCase.repay();
  }
}
