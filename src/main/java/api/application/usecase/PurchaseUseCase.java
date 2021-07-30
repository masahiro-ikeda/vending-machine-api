package api.application.usecase;

import api.application.repository.CashStockRepository;
import api.application.repository.DrinkRepository;
import api.application.repository.PaymentRepository;
import api.application.repository.SoldRepository;
import api.domain.model.drink.DrinkId;
import api.domain.model.purchase.Purchase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ドリンクの購入ユースケース.
 */
@Service
public class PurchaseUseCase {

  private final PaymentRepository paymentRepository;
  private final DrinkRepository drinkRepository;
  private final CashStockRepository cashStockRepository;
  private final SoldRepository soldRepository;

  public PurchaseUseCase(PaymentRepository paymentRepository, DrinkRepository drinkRepository, CashStockRepository cashStockRepository, SoldRepository soldRepository) {
    this.paymentRepository = paymentRepository;
    this.drinkRepository = drinkRepository;
    this.cashStockRepository = cashStockRepository;
    this.soldRepository = soldRepository;
  }

  /**
   * 購入.
   *
   * @param drinkId 購入するドリンクのID
   */
  @Transactional
  public void purchase(String drinkId) {

    // モデルの復元
    var payments = paymentRepository.fetch();
    var drink = drinkRepository.fetchById( new DrinkId( drinkId ) );
    var cashStock = cashStockRepository.fetch();

    // 購入処理
    var purchase = new Purchase( drink, payments );
    var returnAmount = purchase.buy();

    // 現金残高に反映
    cashStock.outCash( returnAmount );

    // 永続化
    soldRepository.store( purchase.sold() );
    drinkRepository.store( drink );
    paymentRepository.store( payments );
    cashStockRepository.store( cashStock );
  }
}
