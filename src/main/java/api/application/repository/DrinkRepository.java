package api.application.repository;

import api.domain.model.drink.Drink;
import api.domain.model.drink.DrinkId;

public interface DrinkRepository {

  /**
   * ドリンク情報を取得.
   *
   * @param drinkId ドリンクID
   * @return ドリンク
   */
  Drink fetchById(DrinkId drinkId);

  /**
   * 入出庫の更新.
   *
   * @param drink ドリンク集約
   */
  void store(Drink drink);
}
