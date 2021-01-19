package usecase;

import domain.model.drink.DrinkDisplay;
import domain.model.payment.Payments;
import domain.service.SearchSaleableDrinkService;
import infrastructure.InMemory.PaymentRepositoryImpl;
import presenter.PaymentPresenter;
import repository.PaymentRepository;

import java.util.List;

/**
 * 支払い処理.
 */
public class PaymentUseCase {

  private PaymentPresenter paymentPresenter;
  private PaymentRepository paymentRepository;
  private SearchSaleableDrinkService searchSaleableDrinkService;

  public PaymentUseCase() {
    paymentPresenter = new PaymentPresenter();
    paymentRepository = new PaymentRepositoryImpl();
    searchSaleableDrinkService = new SearchSaleableDrinkService();
  }

  /**
   * お金を支払う.
   *
   * @param amount 投入した金額
   */
  public void pay(int amount) {

    // お金を投入
    Payments payments = paymentRepository.fetch();
    payments.pay( amount );
    int totalAmount = payments.getTotalAmount();

    // 投入金額合計で購入可能なドリンクのリストを取得
    List<DrinkDisplay> drinkDisplays = searchSaleableDrinkService.getDisplayDrinks( totalAmount );

    // コンソール表示
    paymentPresenter.showMessage( totalAmount, drinkDisplays );
  }
}
