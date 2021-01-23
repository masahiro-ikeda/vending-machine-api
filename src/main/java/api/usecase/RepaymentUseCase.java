package api.usecase;

import api.domain.entity.cash.Cash;
import api.domain.entity.cash.CashStock;
import api.domain.entity.payment.PaymentHolder;
import api.repository.CashStockRepository;
import api.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 返金処理.
 */
@Service
public class RepaymentUseCase {

  private PaymentRepository paymentRepository;
  private CashStockRepository cashStockRepository;

  public RepaymentUseCase(PaymentRepository paymentRepository, CashStockRepository cashStockRepository) {
    this.paymentRepository = paymentRepository;
    this.cashStockRepository = cashStockRepository;
  }

  /**
   * 返金を行う.
   */
  public List<Cash> repay() {

    // 支払金額の合計を取得
    PaymentHolder paymentHolder = paymentRepository.fetch();
    int totalAmount = paymentHolder.getTotalAmount();

    // お釣りの枚数を取得
    CashStock cashStock = cashStockRepository.fetch();
    List<Cash> changes = cashStock.putOut( totalAmount );

    // 支払いをリセット
    paymentHolder.reset();

    // 永続化
    cashStockRepository.store( cashStock );
    paymentRepository.store( paymentHolder );

    return changes;
  }
}
