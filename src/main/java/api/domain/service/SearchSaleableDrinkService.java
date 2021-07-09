package api.domain.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import api.application.repository.DrinkRepository;
import api.infrastructure.InMemory.DrinkData;
import api.presentation.viewmodel.DrinkViewModel;

/**
 * 陳列するドリンクを取得するドメインサービス.
 */
@Component
public class SearchSaleableDrinkService {

  private DrinkRepository drinkRepository;

  public SearchSaleableDrinkService(DrinkRepository drinkRepository) {
    this.drinkRepository = drinkRepository;
  }

  /**
   * 陳列するドリンクを取得する.
   *
   * @return ドリンク一覧
   */
  public List<DrinkViewModel> getDisplayDrinks(int totalAmount) {

    // ドリンク一覧を取得
    List<DrinkData> drinks = drinkRepository.fetch();

    // 表示用モデルに変換
    List<DrinkViewModel> drinkViewModels = new ArrayList<>();
    for (DrinkData drink : drinks) {
      DrinkViewModel drinkViewModel = new DrinkViewModel(
          drink.getDrinkId(),
          drink.getDrinkName(),
          drink.getDrinkPrice(),
          drink.getTemperatureState(),
          drink.getDrinkQuantity() > 0
      );
      drinkViewModels.add( drinkViewModel );
    }

    return drinkViewModels;
  }
}
