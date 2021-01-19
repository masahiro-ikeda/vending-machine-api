package usecase;

import domain.model.payment.Payments;
import infrastructure.InMemory.PaymentRepositoryImpl;
import presenter.RepaymentPresenter;
import repository.PaymentRepository;

/**
 * 返金処理.
 */
public class RepaymentUseCase {

  private RepaymentPresenter repaymentPresenter;
  private PaymentRepository paymentRepository;

  public RepaymentUseCase() {
    repaymentPresenter = new RepaymentPresenter();
    paymentRepository = new PaymentRepositoryImpl();
  }

  /**
   * 返金を行う.
   */
  public void repay() {

    // 支払金額の合計を取得
    Payments payments = paymentRepository.fetch();
    int totalAmount = payments.getTotalAmount();

    // コンソール表示
    repaymentPresenter.showMessage( totalAmount );
  }
}
