package api.application;

import org.springframework.stereotype.Service;
import api.application.repository.CashManagerRepository;
import api.application.repository.PaymentRepository;
import api.domain.model.payment.Payment;
import api.domain.model.payment.PaymentAmount;
import api.domain.model.payment.YenCurrency;

/**
 * 支払いユースケース.
 */
@Service
public class PaymentUseCase {

  private final PaymentRepository paymentRepository;
  private final CashManagerRepository cashManagerRepository;

  public PaymentUseCase(PaymentRepository paymentRepository, CashManagerRepository cashManagerRepository) {
    this.paymentRepository = paymentRepository;
    this.cashManagerRepository = cashManagerRepository;
  }

  /**
   * お金を支払う.
   *
   * @param inputAmount 投入した金額
   * @return 支払い済み金額
   */
  public int pay(int inputAmount) {

    // 支払い
    var inputMoney = YenCurrency.of( inputAmount );
    var payment = Payment.newPay( inputMoney );

    // 現金残高を増やす
//    CashStocks cashStocks = cashManagerRepository.fetch();
//    cashStocks.add( payment );

    //永続化
    paymentRepository.store( payment );
    //cashManagerRepository.store( cashStocks );

    return new PaymentAmount( paymentRepository.fetch() ).value();
  }
}
