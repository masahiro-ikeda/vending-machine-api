package api.application;

import api.domain.entity.payment.PaymentHolder;
import api.domain.service.SearchSaleableDrinkService;
import api.presentation.viewmodel.DrinkViewModel;
import api.presentation.viewmodel.VendingMachineViewModel;
import api.domain.repository.PaymentRepository;
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
   * 自動販売機を表示させるためのモデルを取得.
   *
   * @return 自動販売機の画面表示モデル
   */
  public VendingMachineViewModel searchDrinkDisplays() {

    // 飲料一覧を取得
    PaymentHolder paymentHolder = paymentRepository.fetch();
    int totalAmount = paymentHolder.getTotalAmount();
    List<DrinkViewModel> drinkViewModels =  searchSaleableDrinkService.getDisplayDrinks( totalAmount );

    return new VendingMachineViewModel( drinkViewModels, totalAmount );
  }
}
