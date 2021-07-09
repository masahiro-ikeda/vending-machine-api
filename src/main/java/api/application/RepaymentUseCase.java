package api.application;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import api.application.repository.CashManagerRepository;
import api.application.repository.PaymentRepository;
import api.domain.entity.cash.Cash;
import api.domain.entity.cash.CashManager;
import api.domain.model.payment.PaymentAmount;
import api.domain.model.payment.Payments;
import api.presentation.viewmodel.CashViewModel;
import api.presentation.viewmodel.RepaymentViewModel;

/**
 * 返金処理.
 */
@Service
public class RepaymentUseCase {

  private PaymentRepository paymentRepository;
  private CashManagerRepository cashManagerRepository;

  public RepaymentUseCase(PaymentRepository paymentRepository, CashManagerRepository cashManagerRepository) {
    this.paymentRepository = paymentRepository;
    this.cashManagerRepository = cashManagerRepository;
  }

  /**
   * 返金を行う.
   */
  public RepaymentViewModel repay() {

    // 支払金額の合計を取得
    Payments payments = paymentRepository.fetch();
    int totalAmount = new PaymentAmount(payments).value();

    // お釣りの枚数を取得
    CashManager cashManager = cashManagerRepository.fetch();
    List<Cash> repayments = cashManager.take( totalAmount );

    // 支払いをリセット
    payments.reset();

    // 永続化
    cashManagerRepository.store( cashManager );
    //paymentRepository.store( payments );

    List<CashViewModel> cashViewModels = repayments.stream()
        .map( cash -> new CashViewModel( cash.getYenCurrency().value(), cash.getCashQuantity().value() ) ).collect( Collectors.toList() );

    return new RepaymentViewModel( cashViewModels );
  }
}
