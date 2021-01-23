package api.usecase;

import api.domain.entity.drink.DrinkDisplay;
import api.domain.entity.payment.PaymentHolder;
import api.domain.service.SearchSaleableDrinkService;
import api.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 表示するドリンクを取得するユースケース.
 */
@Service
public class DisplayUseCase {

  private PaymentRepository paymentRepository;
  private SearchSaleableDrinkService searchSaleableDrinkService;

  public DisplayUseCase(PaymentRepository paymentRepository, SearchSaleableDrinkService searchSaleableDrinkService) {
    this.paymentRepository = paymentRepository;
    this.searchSaleableDrinkService = searchSaleableDrinkService;
  }

  /**
   * 表示するドリンク一覧を取得.
   *
   * @return 陳列するドリンク一覧
   */
  public List<DrinkDisplay> searchDrinkDisplays() {
    PaymentHolder paymentHolder = paymentRepository.fetch();
    int totalAmount = paymentHolder.getTotalAmount();
    return searchSaleableDrinkService.getDisplayDrinks( totalAmount );
  }
}
