package api.application;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import api.application.repository.CashManagerRepository;
import api.application.repository.DrinkRepository;
import api.application.repository.PaymentRepository;
import api.domain.model.cash.CashStock;
import api.domain.model.cash.CashStocks;
import api.domain.model.drink.Drink;
import api.domain.valueobject.Quantity;
import api.domain.model.payment.PaymentAmount;
import api.domain.model.payment.Payments;
import api.presentation.viewmodel.CashViewModel;
import api.presentation.viewmodel.DrinkViewModel;
import api.presentation.viewmodel.PurchaseViewModel;

/**
 * ドリンクの購入ユースケース.
 */
@Service
public class PurchaseUseCase {

  private PaymentRepository paymentRepository;
  private DrinkRepository drinkRepository;
  private CashManagerRepository cashManagerRepository;

  public PurchaseUseCase(PaymentRepository paymentRepository, DrinkRepository drinkRepository, CashManagerRepository cashManagerRepository) {
    this.paymentRepository = paymentRepository;
    this.drinkRepository = drinkRepository;
    this.cashManagerRepository = cashManagerRepository;
  }

  /**
   * 購入.
   *
   * @param drinkId 購入するドリンクのID
   */
  public PurchaseViewModel buy(int drinkId) {

    // 支払い済み金額を取得
    Payments payments = paymentRepository.fetch();
    int totalAmount = new PaymentAmount(payments).value();

    // 購入可能かチェック
    Drink drink = drinkRepository.fetchById( drinkId );
    if (drink == null) {
      throw new RuntimeException( "Illegal drinkId." );
    }
    if (!drink.canPurchase( new PaymentAmount(payments), new Quantity(1) )) {
      throw new RuntimeException( "Shortage Payment Amount." );
    }

    // 購入処理
    //drink.ship(); // ドリンクを出庫
    payments.repay(); // 支払いをリセット

    // お釣りを返却
    int changeAmount = totalAmount - drink.getDrinkPrice().value();
    CashStocks cashStocks = cashManagerRepository.fetch();
    // お釣りが足りるか？
    if (changeAmount > cashStocks.getTotalAmount()) {
      throw new RuntimeException( "Shortage CashStock For Change." );
    }
    List<CashStock> changes = cashStocks.take( changeAmount );

    // 永続化
    //drinkRepository.store( drink );
    cashManagerRepository.store( cashStocks );
    //paymentRepository.store( payments );

    List<CashViewModel> cashViewModels = changes.stream()
        .map( cash -> new CashViewModel( cash.getYenCurrency().value(), cash.getCashQuantity().value() ) ).collect( Collectors.toList() );

    DrinkViewModel drinkViewModel = new DrinkViewModel(
        drink.getDrinkId(),
        "test",
        drink.getDrinkPrice().value(),
        "cold",
        drink.canPurchase(new PaymentAmount(payments), new Quantity(1))
    );

    return new PurchaseViewModel( drinkViewModel, cashViewModels );
  }
}
