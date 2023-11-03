package api.application.sale;

import api.domain.drink.DrinkRepository;
import api.domain.payment.PaymentRepository;
import api.domain.sale.SaleRepository;
import api.domain.drink.Drink;
import api.domain.payment.Payments;
import api.domain.sale.Sale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ドリンクの販売ユースケース.
 */
@Service
public class SaleUseCase {

  private final PaymentRepository paymentRepository;
  private final DrinkRepository drinkRepository;
  private final SaleRepository saleRepository;

  public SaleUseCase(PaymentRepository paymentRepository, DrinkRepository drinkRepository, SaleRepository saleRepository) {
    this.paymentRepository = paymentRepository;
    this.drinkRepository = drinkRepository;
    this.saleRepository = saleRepository;
  }

  /**
   * 販売.
   */
  @Transactional(rollbackFor = Exception.class)
  public SaleOutput execute(SaleInput input) {

    Payments payments = paymentRepository.fetch();
    Drink drink = drinkRepository.fetchById(input.getDrinkId());

    // 自動販売機なので1缶ずつ販売する
    int saleQuantity = 1;

    // 販売可能かチェックする
    drink.validateOnSale(saleQuantity, payments.totalAmount());

    // ドリンク出庫記録に反映
    String drinkInoutId = drink.out(saleQuantity);
    drinkRepository.store(drink);

    // 支払記録に反映
    String paymentId = payments.sale(drink.drinkPrice());
    paymentRepository.store(payments);

    // 販売記録の作成
    Sale sale = new Sale(drink.drinkId(), saleQuantity, paymentId, drinkInoutId);
    saleRepository.store(sale);

    return new SaleOutput(payments.totalAmount());
  }
}
