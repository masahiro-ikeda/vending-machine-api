package usecase;

import domain.model.cash.CashStock;
import domain.model.drink.DrinkDisplay;
import domain.model.payment.Payment;
import domain.model.payment.Payments;
import domain.service.SearchSaleableDrinkService;
import infrastructure.InMemory.CashStockRepositoryImpl;
import infrastructure.InMemory.PaymentRepositoryImpl;
import presenter.PaymentPresenter;
import repository.CashStockRepository;
import repository.PaymentRepository;

import java.util.List;

/**
 * 支払い処理.
 */
public class PaymentUseCase {

  private PaymentPresenter paymentPresenter;
  private PaymentRepository paymentRepository;
  private CashStockRepository cashStockRepository;
  private SearchSaleableDrinkService searchSaleableDrinkService;

  public PaymentUseCase() {
    paymentPresenter = new PaymentPresenter();
    paymentRepository = new PaymentRepositoryImpl();
    cashStockRepository = new CashStockRepositoryImpl();
    searchSaleableDrinkService = new SearchSaleableDrinkService();
  }

  /**
   * お金を支払う.
   *
   * @param amount 投入した金額
   */
  public void pay(int amount) {

    // 支払い
    Payment payment = new Payment( amount );
    Payments payments = paymentRepository.fetch();
    payments.pay( payment );

    // 現金残高を増やす
    CashStock cashStock = cashStockRepository.fetch();
    cashStock.putIn( payment );

    // 投入金額合計で購入可能なドリンクのリストを取得
    int totalAmount = payments.getTotalAmount();
    List<DrinkDisplay> drinkDisplays = searchSaleableDrinkService.getDisplayDrinks( totalAmount );

    //永続化
    paymentRepository.store( payments );
    cashStockRepository.store( cashStock );

    // コンソール表示
    paymentPresenter.showMessage( totalAmount, drinkDisplays );
  }
}
