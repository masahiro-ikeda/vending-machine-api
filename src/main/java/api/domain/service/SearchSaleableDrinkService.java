package api.domain.service;

import api.domain.entity.drink.Drink;
import api.presentation.viewmodel.DrinkViewModel;
import api.domain.repository.DrinkRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    List<Drink> drinks = drinkRepository.fetch();

    // 表示用モデルに変換
    List<DrinkViewModel> drinkViewModels = new ArrayList<>();
    for (Drink drink : drinks) {
      DrinkViewModel drinkViewModel = new DrinkViewModel(
          drink.getDrinkId(),
          drink.getDrinkName().value(),
          drink.getDrinkPrice().intValue(),
          drink.getTemperatureState().getValue(),
          drink.isSaleable( totalAmount )
      );
      drinkViewModels.add( drinkViewModel );
    }

    return drinkViewModels;
  }
}
