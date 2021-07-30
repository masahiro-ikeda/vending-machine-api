package api.application.usecase;

import api.application.repository.CashStockRepository;
import api.application.repository.PaymentRepository;
import api.domain.model.payments.ReturnAmount;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  @Transactional
  public void repay() {
    // モデルの復元
    var payments = paymentRepository.fetch();
    var cashStock = cashStockRepository.fetch();

    // 返金を実施
    ReturnAmount returnAmount = payments.repay();
    cashStock.outCash( returnAmount );

    paymentRepository.store( payments );
    cashStockRepository.store( cashStock );
  }
}
