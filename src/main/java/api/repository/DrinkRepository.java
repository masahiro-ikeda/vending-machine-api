package api.repository;

import api.domain.entity.drink.Drink;

import java.util.List;

public interface DrinkRepository {

  /**
   * 全てのドリンクを取得.
   *
   * @return 全ドリンク
   */
  List<Drink> fetch();

  /**
   * ドリンク情報を取得.
   *
   * @param drinkId ドリンクID
   * @return ドリンク
   */
  Drink fetchById(int drinkId);

  /**
   * ドリンク情報の更新.
   *
   * @param drink ドリンク
   */
  void store(Drink drink);
}
