package domain.service;

import domain.model.drink.Drink;
import domain.model.drink.DrinkDisplay;
import infrastructure.InMemory.DrinkRepositoryImpl;
import repository.DrinkRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 陳列するドリンクを取得するドメインサービス.
 */
public class SearchSaleableDrinkService {

  private DrinkRepository drinkRepository;

  /**
   * コンストラクタ.
   */
  public SearchSaleableDrinkService() {
    drinkRepository = new DrinkRepositoryImpl();
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
          drink.isSaleable( totalAmount )
      );
      drinkDisplays.add( drinkDisplay );
    }

    return drinkDisplays;
  }
}
