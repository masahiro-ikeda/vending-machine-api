package api.application.usecase;

import api.application.repository.CashStockRepository;
import api.application.repository.PaymentRepository;
import api.domain.model.YenCurrency;
import api.domain.model.payments.PaymentTotalAmount;
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
   *
   * @param inputAmount 投入した金額
   * @return 支払い済み金額
   */
  @Transactional
  public PaymentTotalAmount pay(int inputAmount) {

    var inputYenCurrency = YenCurrency.of( inputAmount );

    // モデルの復元
    var payments = paymentRepository.fetch();
    var cashStock = cashStockRepository.fetch();

    // 支払を実施
    payments.pay( inputYenCurrency );

    // 現金残高に反映
    cashStock.inCash( inputYenCurrency );

    //永続化
    paymentRepository.store( payments );
    cashStockRepository.store( cashStock );

    return payments.paymentTotalAmount();
  }
}
