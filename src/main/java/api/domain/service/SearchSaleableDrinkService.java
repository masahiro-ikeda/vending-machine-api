package api.domain.service;

import api.domain.entity.drink.Drink;
import api.domain.entity.drink.DrinkDisplay;
import api.repository.DrinkRepository;
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
  public List<DrinkDisplay> getDisplayDrinks(int totalAmount) {

    // ドリンク一覧を取得
    List<Drink> drinks = drinkRepository.fetch();

    // 陳列用のモデルに変換
    List<DrinkDisplay> drinkDisplays = new ArrayList<>();
    for (Drink drink : drinks) {
      DrinkDisplay drinkDisplay = new DrinkDisplay(
          drink.getDrinkId(),
          drink.getDrinkName(),
          drink.getDrinkPrice(),
          drink.isSaleable( totalAmount ),
          drink.getDrinkTemperatureType().getValue()
      );
      drinkDisplays.add( drinkDisplay );
    }

    return drinkDisplays;
  }
}
