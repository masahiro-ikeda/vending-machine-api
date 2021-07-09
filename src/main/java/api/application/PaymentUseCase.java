package api.application;

import api.domain.entity.cash.CashManager;
import api.domain.model.payment.Payment;
import api.domain.model.payment.PaymentHolder;
import api.application.repository.CashManagerRepository;
import api.application.repository.PaymentRepository;
import api.domain.model.payment.YenCurrency;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 支払いユースケース.
 */
@Service
public class PaymentUseCase {

  private PaymentRepository paymentRepository;
  private CashManagerRepository cashManagerRepository;

  public PaymentUseCase(PaymentRepository paymentRepository, CashManagerRepository cashManagerRepository) {
    this.paymentRepository = paymentRepository;
    this.cashManagerRepository = cashManagerRepository;
  }

  /**
   * お金を支払う.
   *
   * @param amount 投入した金額
   * @return 支払い済み金額
   */
  public int pay(int amount) {

    // 支払い
    Payment payment = new Payment(
        YenCurrency.of( amount ),
        LocalDateTime.now()
    );
    PaymentHolder paymentHolder = paymentRepository.fetch();
    paymentHolder.pay( payment );

    // 現金残高を増やす
    CashManager cashManager = cashManagerRepository.fetch();
    cashManager.add( payment );

    //永続化
    paymentRepository.store( paymentHolder );
    cashManagerRepository.store( cashManager );

    return paymentHolder.getTotalAmount();
  }
}
