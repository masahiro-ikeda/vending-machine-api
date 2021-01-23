package usecase;

import domain.model.cash.Cash;
import domain.model.cash.CashStock;
import domain.model.drink.Drink;
import domain.model.payment.Payments;
import infrastructure.InMemory.CashStockRepositoryImpl;
import infrastructure.InMemory.DrinkRepositoryImpl;
import infrastructure.InMemory.PaymentRepositoryImpl;
import presenter.PurchasePresenter;
import repository.CashStockRepository;
import repository.DrinkRepository;
import repository.PaymentRepository;

import java.util.List;

/**
 * ドリンクの購入処理.
 */
public class PurchaseUseCase {

  private PaymentRepository paymentRepository;
  private DrinkRepository drinkRepository;
  private CashStockRepository cashStockRepository;
  private PurchasePresenter purchasePresenter;

  /**
   * コンストラクタ.
   */
  public PurchaseUseCase() {
    paymentRepository = new PaymentRepositoryImpl();
    drinkRepository = new DrinkRepositoryImpl();
    cashStockRepository = new CashStockRepositoryImpl();
    purchasePresenter = new PurchasePresenter();
  }

  public void buy(int drinkId) {

    // 支払い済み金額を取得
    Payments payments = paymentRepository.fetch();
    int totalAmount = payments.getTotalAmount();

    // 購入可能かチェック
    Drink drink = drinkRepository.fetchById( drinkId );
    if (drink == null) {
      throw new RuntimeException();
    }
    if (!drink.isSaleable( totalAmount )) {
      throw new RuntimeException();
    }

    // ドリンクを出庫
    drink.ship();

    // お釣りを返却
    int changeAmount = totalAmount - drink.getDrinkPrice();
    CashStock cashStock = cashStockRepository.fetch();
    // お釣りが足りるか？
    if (changeAmount > cashStock.getTotalCashAmount()) {
      throw new RuntimeException();
    }
    List<Cash> changes = cashStock.putOut( changeAmount );

    // 永続化
    drinkRepository.store( drink );
    cashStockRepository.store( cashStock );
    paymentRepository.release();

    // コンソール表示
    purchasePresenter.showMessage( drink.getDrinkName(), changes );
  }
}
