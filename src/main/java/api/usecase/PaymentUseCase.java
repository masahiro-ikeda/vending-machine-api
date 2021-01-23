package api.usecase;

import api.domain.entity.cash.CashStock;
import api.domain.entity.drink.DrinkDisplay;
import api.domain.entity.payment.Payment;
import api.domain.entity.payment.PaymentHolder;
import api.domain.service.SearchSaleableDrinkService;
import api.repository.CashStockRepository;
import api.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 支払いユースケース.
 */
@Service
public class PaymentUseCase {

  private PaymentRepository paymentRepository;
  private CashStockRepository cashStockRepository;
  private SearchSaleableDrinkService searchSaleableDrinkService;

  public PaymentUseCase(PaymentRepository paymentRepository, CashStockRepository cashStockRepository, SearchSaleableDrinkService searchSaleableDrinkService) {
    this.paymentRepository = paymentRepository;
    this.cashStockRepository = cashStockRepository;
    this.searchSaleableDrinkService = searchSaleableDrinkService;
  }

  /**
   * お金を支払う.
   *
   * @param amount 投入した金額
   */
  public List<DrinkDisplay> pay(int amount) {

    // 支払い
    Payment payment = new Payment( amount );
    PaymentHolder paymentHolder = paymentRepository.fetch();
    paymentHolder.pay( payment );

    // 現金残高を増やす
    CashStock cashStock = cashStockRepository.fetch();
    cashStock.putIn( payment );

    // 投入金額合計で購入可能なドリンクのリストを取得
    int totalAmount = paymentHolder.getTotalAmount();
    List<DrinkDisplay> drinkDisplays = searchSaleableDrinkService.getDisplayDrinks( totalAmount );

    //永続化
    paymentRepository.store( paymentHolder );
    cashStockRepository.store( cashStock );

    // コンソール表示
    return drinkDisplays;
  }
}
