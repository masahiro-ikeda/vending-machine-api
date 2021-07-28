package api.presentation.controler;

import api.application.PaymentUseCase;
import api.application.RepaymentUseCase;
import api.presentation.controler.form.PaymentForm;
import api.presentation.viewmodel.RepaymentViewModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支払いコントローラー.
 */
@RestController
public class PaymentController {

  private final PaymentUseCase paymentUseCase;
  private final RepaymentUseCase repaymentUseCase;

  public PaymentController(PaymentUseCase paymentUseCase, RepaymentUseCase repaymentUseCase) {
    this.paymentUseCase = paymentUseCase;
    this.repaymentUseCase = repaymentUseCase;
  }

  @PostMapping("pay")
  public int pay(@Validated @RequestBody PaymentForm form) {
    return paymentUseCase.pay( form.getAmount() );
  }

  @PostMapping("repay")
  public RepaymentViewModel repay() {
    return repaymentUseCase.repay();
  }

}
