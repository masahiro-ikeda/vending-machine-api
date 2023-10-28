package api.domain.drink;

public interface DrinkRepository {

  /**
   * ドリンク情報を取得.
   */
  Drink fetchById(String drinkId);

  /**
   * 入出庫の更新.
   */
  void store(Drink drink);
}
