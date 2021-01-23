package usecase;

import domain.model.cash.Cash;
import domain.model.cash.CashStock;
import domain.model.payment.PaymentHolder;
import infrastructure.InMemory.CashStockRepositoryImpl;
import infrastructure.InMemory.PaymentRepositoryImpl;
import presenter.RepaymentPresenter;
import repository.CashStockRepository;
import repository.PaymentRepository;

import java.util.List;

/**
 * 返金処理.
 */
public class RepaymentUseCase {

  private RepaymentPresenter repaymentPresenter;
  private PaymentRepository paymentRepository;
  private CashStockRepository cashStockRepository;

  public RepaymentUseCase() {
    repaymentPresenter = new RepaymentPresenter();
    paymentRepository = new PaymentRepositoryImpl();
    cashStockRepository = new CashStockRepositoryImpl();
  }

  /**
   * 返金を行う.
   */
  public void repay() {

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

    // コンソール表示
    repaymentPresenter.showMessage( changes );
  }
}
