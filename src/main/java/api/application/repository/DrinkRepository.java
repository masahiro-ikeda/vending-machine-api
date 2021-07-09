package api.application.repository;

import java.util.List;
import api.domain.model.drink.Drink;
import api.infrastructure.InMemory.DrinkData;

public interface DrinkRepository {

  /**
   * 全てのドリンクを取得.
   *
   * @return 全ドリンク
   */
  List<DrinkData> fetch();

  /**
   * ドリンク情報を取得.
   *
   * @param drinkId ドリンクID
   * @return ドリンク
   */
  Drink fetchById(int drinkId);
}
