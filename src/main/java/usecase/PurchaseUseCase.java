package usecase;

import domain.model.drink.Drink;
import domain.model.payment.Payments;
import infrastructure.InMemory.DrinkRepositoryImpl;
import infrastructure.InMemory.PaymentRepositoryImpl;
import presenter.PurchasePresenter;
import repository.DrinkRepository;
import repository.PaymentRepository;

/**
 * ドリンクの購入処理.
 */
public class PurchaseUseCase {

  private PaymentRepository paymentRepository;
  private DrinkRepository drinkRepository;
  private PurchasePresenter purchasePresenter;

  /**
   * コンストラクタ.
   */
  public PurchaseUseCase() {
    paymentRepository = new PaymentRepositoryImpl();
    drinkRepository = new DrinkRepositoryImpl();
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
    purchasePresenter.showMessage( drink.getDrinkName(), changeAmount );

    // お釣りを返却したので支払記録を消去
    paymentRepository.release();
  }
}
