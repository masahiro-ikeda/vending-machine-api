package api.usecase;

import api.domain.entity.cash.Cash;
import api.domain.entity.cash.CashStock;
import api.domain.entity.drink.Drink;
import api.domain.entity.payment.PaymentHolder;
import api.repository.CashStockRepository;
import api.repository.DrinkRepository;
import api.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ドリンクの購入ユースケース.
 */
@Service
public class PurchaseUseCase {

  private PaymentRepository paymentRepository;
  private DrinkRepository drinkRepository;
  private CashStockRepository cashStockRepository;

  public PurchaseUseCase(PaymentRepository paymentRepository, DrinkRepository drinkRepository, CashStockRepository cashStockRepository) {
    this.paymentRepository = paymentRepository;
    this.drinkRepository = drinkRepository;
    this.cashStockRepository = cashStockRepository;
  }

  /**
   * 購入.
   *
   * @param drinkId 購入するドリンクのID
   */
  public List<Cash> buy(int drinkId) {

    // 支払い済み金額を取得
    PaymentHolder paymentHolder = paymentRepository.fetch();
    int totalAmount = paymentHolder.getTotalAmount();

    // 購入可能かチェック
    Drink drink = drinkRepository.fetchById( drinkId );
    if (drink == null) {
      throw new RuntimeException();
    }
    if (!drink.isSaleable( totalAmount )) {
      throw new RuntimeException();
    }

    // 購入処理
    drink.ship(); // ドリンクを出庫
    paymentHolder.reset(); // 支払いをリセット

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
    paymentRepository.store( paymentHolder );

    // コンソール表示
    return changes;
  }
}
