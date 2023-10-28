package api.application;

import java.util.List;
import org.springframework.stereotype.Service;
import api.application.repository.PaymentRepository;
import api.domain.payments.PaymentAmount;
import api.domain.payments.Payments;
import api.domain.service.SearchSaleableDrinkService;
import api.presentation.viewmodel.DrinkViewModel;

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
    Payments payments = paymentRepository.fetch();
    PaymentAmount paymentAmount = new PaymentAmount(payments);

    // 飲料の画面表示モデル一覧を取得
    List<DrinkViewModel> drinkViewModels = searchSaleableDrinkService.getDisplayDrinks( paymentAmount.value() );

    return drinkViewModels;
  }

  /**
   * 支払い済み金額を取得.
   *
   * @return 飲料の画面表示モデル一覧
   */
  public int fetchPaymentAmount() {
    // 支払い済み金額を取得
    Payments payments = paymentRepository.fetch();
    PaymentAmount paymentAmount = new PaymentAmount(payments);

    return paymentAmount.value();
  }
}
