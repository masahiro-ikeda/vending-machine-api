package api.application;

import api.domain.entity.cash.Cash;
import api.domain.entity.cash.CashManager;
import api.domain.entity.drink.Drink;
import api.domain.model.payment.PaymentHolder;
import api.presentation.viewmodel.CashViewModel;
import api.presentation.viewmodel.DrinkViewModel;
import api.presentation.viewmodel.PurchaseViewModel;
import api.application.repository.CashManagerRepository;
import api.application.repository.DrinkRepository;
import api.application.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    PaymentHolder paymentHolder = paymentRepository.fetch();
    int totalAmount = paymentHolder.getTotalAmount();

    // 購入可能かチェック
    Drink drink = drinkRepository.fetchById( drinkId );
    if (drink == null) {
      throw new RuntimeException( "Illegal drinkId." );
    }
    if (!drink.isSaleable( totalAmount )) {
      throw new RuntimeException( "Shortage Payment Amount." );
    }

    // 購入処理
    drink.ship(); // ドリンクを出庫
    paymentHolder.reset(); // 支払いをリセット

    // お釣りを返却
    int changeAmount = totalAmount - drink.getDrinkPrice().intValue();
    CashManager cashManager = cashManagerRepository.fetch();
    // お釣りが足りるか？
    if (changeAmount > cashManager.getTotalAmount()) {
      throw new RuntimeException( "Shortage Cash For Change." );
    }
    List<Cash> changes = cashManager.take( changeAmount );

    // 永続化
    drinkRepository.store( drink );
    cashManagerRepository.store( cashManager );
    paymentRepository.store( paymentHolder );

    List<CashViewModel> cashViewModels = changes.stream()
        .map( cash -> new CashViewModel( cash.getYenCurrency().value(), cash.getCashQuantity().intValue() ) ).collect( Collectors.toList() );

    DrinkViewModel drinkViewModel = new DrinkViewModel(
        drink.getDrinkId(),
        drink.getDrinkName().value(),
        drink.getDrinkPrice().intValue(),
        drink.getTemperatureState().getValue(),
        drink.isSaleable( totalAmount )
    );

    return new PurchaseViewModel( drinkViewModel, cashViewModels );
  }
}
