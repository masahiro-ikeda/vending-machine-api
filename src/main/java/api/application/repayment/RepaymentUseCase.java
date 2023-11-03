package api.application.repayment;

import api.domain.cash.CashStockRepository;
import api.domain.payment.PaymentRepository;
import api.domain.cash.CashStocks;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 返金処理.
 */
@Service
public class RepaymentUseCase {

  private final PaymentRepository paymentRepository;
  private final CashStockRepository cashStockRepository;

  public RepaymentUseCase(PaymentRepository paymentRepository, CashStockRepository cashStockRepository) {
    this.paymentRepository = paymentRepository;
    this.cashStockRepository = cashStockRepository;
  }

  /**
   * 返金を行う.
   */
  @Transactional(rollbackFor = Exception.class)
  public RepaymentOutput execute() {

    // 返金
    var payments = paymentRepository.fetch();
    int repaymentAmount = payments.totalAmount(); // 返金金額
    payments.repay();
    paymentRepository.store(payments);

    // 返金分の現金を取り出し
    CashStocks cashStock = cashStockRepository.fetch();
    Map<Integer, Integer> repays = cashStock.repay(repaymentAmount);
    cashStockRepository.store(cashStock);

    return new RepaymentOutput(repays);
  }
}
