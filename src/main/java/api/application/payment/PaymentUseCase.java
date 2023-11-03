package api.application.payment;

import api.domain.cash.CashStockRepository;
import api.domain.payment.PaymentRepository;
import api.domain.YenCurrency;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 支払いユースケース.
 */
@Service
public class PaymentUseCase {

  private final PaymentRepository paymentRepository;
  private final CashStockRepository cashStockRepository;

  public PaymentUseCase(PaymentRepository paymentRepository, CashStockRepository cashStockRepository) {
    this.paymentRepository = paymentRepository;
    this.cashStockRepository = cashStockRepository;
  }

  /**
   * お金を支払う.
   */
  @Transactional(rollbackFor = Exception.class)
  public PaymentOutput execute(PaymentInput input) {

    YenCurrency paymentCurrency = YenCurrency.of(input.getPaymentAmount());

    // 支払い
    var payments = paymentRepository.fetch();
    payments.pay(paymentCurrency);
    paymentRepository.store(payments);

    // 現金残高に反映
    var cashStock = cashStockRepository.fetch();
    cashStock.in(paymentCurrency);
    cashStockRepository.store(cashStock);

    int totalPaymentAmount = payments.totalAmount();
    return new PaymentOutput(totalPaymentAmount);
  }
}
