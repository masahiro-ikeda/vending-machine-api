package api.presentation.controler;

import api.application.usecase.PaymentUseCase;
import api.application.usecase.RepaymentUseCase;
import api.domain.model.payments.PaymentTotalAmount;
import api.presentation.controler.form.PaymentForm;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支払コントローラー.
 */
@RestController
@RequestMapping("api")
public class PaymentController {

  private final PaymentUseCase paymentUseCase;
  private final RepaymentUseCase repaymentUseCase;

  public PaymentController(PaymentUseCase paymentUseCase, RepaymentUseCase repaymentUseCase) {
    this.paymentUseCase = paymentUseCase;
    this.repaymentUseCase = repaymentUseCase;
  }

  /**
   * 支払.
   *
   * @param form
   * @return 支払い済みの金額
   */
  @PostMapping("pay")
  public int pay(@Validated @RequestBody PaymentForm form) {
    PaymentTotalAmount paymentTotalAmount = paymentUseCase.pay( form.getAmount() );
    return paymentTotalAmount.value();
  }

  /**
   * 返金.
   */
  @PostMapping("repay")
  public void repay() {
    repaymentUseCase.repay();
  }
}
