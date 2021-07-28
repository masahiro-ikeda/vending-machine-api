package api.domain.model.payment;

import lombok.AllArgsConstructor;

import java.util.List;

/**
 * 支払の集約.
 */
@AllArgsConstructor
public class Payments {
  private final List<Payment> payments;

  /**
   * 支払いリスト.
   */
  public List<Payment> payments() {
    return List.copyOf( payments );
  }

  /**
   * 返金.
   */
  public Payment repay() {
    PaymentAmount repayAmount = new PaymentAmount( this );
    Payment repayment = Payment.newRepay( repayAmount );
    payments.add( repayment );
    return repayment;
  }
}
