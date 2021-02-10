package api.application;

import api.domain.entity.payment.PaymentHolder;
import api.domain.repository.PaymentRepository;
import api.domain.service.SearchSaleableDrinkService;
import api.presentation.viewmodel.DrinkViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 画面表示に関するデータを取得するユースケース.
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
   * 飲料一覧を取得.
   *
   * @return 飲料の画面表示モデル一覧
   */
  public List<DrinkViewModel> searchDrinks() {

    // 支払い済み金額を取得
    PaymentHolder paymentHolder = paymentRepository.fetch();
    int totalAmount = paymentHolder.getTotalAmount();

    // 飲料の画面表示モデル一覧を取得
    List<DrinkViewModel> drinkViewModels = searchSaleableDrinkService.getDisplayDrinks( totalAmount );

    return drinkViewModels;
  }

  /**
   * 支払い済み金額を取得.
   *
   * @return 飲料の画面表示モデル一覧
   */
  public int fetchPaymentAmount() {
    PaymentHolder paymentHolder = paymentRepository.fetch();
    return paymentHolder.getTotalAmount();
  }
}
