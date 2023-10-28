package api.presentation.controler;

import api.application.payment.PaymentInput;
import api.application.payment.PaymentOutput;
import api.application.payment.PaymentUseCase;
import api.application.repayment.RepaymentOutput;
import api.application.repayment.RepaymentUseCase;
import api.presentation.form.PaymentForm;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 支払コントローラー.
 */
@RestController
class PaymentController {

  private final PaymentUseCase paymentUseCase;
  private final RepaymentUseCase repaymentUseCase;

  PaymentController(PaymentUseCase paymentUseCase, RepaymentUseCase repaymentUseCase) {
    this.paymentUseCase = paymentUseCase;
    this.repaymentUseCase = repaymentUseCase;
  }

  /**
   * 支払.
   */
  @PostMapping("api/pay")
  public PaymentOutput pay(@Validated @RequestBody PaymentForm form) {

    PaymentInput inputData = new PaymentInput(form.getPaymentAmount());
    PaymentOutput outputData = paymentUseCase.execute(inputData);

    return outputData;
  }

  /**
   * 返金.
   */
  @PutMapping("api/repay")
  public Map<Integer, Integer> repay() {
    RepaymentOutput outputData = repaymentUseCase.execute();
    return outputData.getRepays();
  }
}
