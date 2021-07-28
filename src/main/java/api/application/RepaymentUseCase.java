package api.application;

import java.util.List;
import java.util.stream.Collectors;

import api.domain.model.payment.Payment;
import org.springframework.stereotype.Service;
import api.application.repository.CashManagerRepository;
import api.application.repository.PaymentRepository;
import api.domain.model.cash.CashStock;
import api.domain.model.cash.CashStocks;
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

    // 返金記録を作成
    var payments = paymentRepository.fetch();
    var repayment = payments.repay();
    paymentRepository.store( repayment );

    int totalAmount = repayment.value();

    // お釣りの枚数を取得
    CashStocks cashStocks = cashManagerRepository.fetch();
    List<CashStock> repayments = cashStocks.take( totalAmount );

    // 永続化
    cashManagerRepository.store( cashStocks );
    //paymentRepository.store( payments );

    List<CashViewModel> cashViewModels = repayments.stream()
        .map( cash -> new CashViewModel( cash.getYenCurrency().value(), cash.getCashQuantity().value() ) ).collect( Collectors.toList() );

    return new RepaymentViewModel( cashViewModels );
  }
}
