package domain.model.drink;

public class Drink {
  private int drinkId;
  private String drinkName;
  private int drinkPrice;
  private int drinkStockQuantity;

  public Drink(int drinkId, String drinkName, int drinkPrice, int drinkStockQuantity) {
    this.drinkId = drinkId;
    this.drinkName = drinkName;
    this.drinkPrice = drinkPrice;
    this.drinkStockQuantity = drinkStockQuantity;
  }

  /**
   * 購入可能か？
   *
   * @param amount 金額
   * @return 購入可能な場合true
   */
  public boolean isSaleable(int amount) {

    // 価格チェック
    if (amount < drinkPrice) {
      return false;
    }

    // 在庫チェック
    if (drinkStockQuantity <= 0) {
      return false;
    }

    return true;
  }

  /**
   * 在庫の出荷（売れた）.
   */
  public void ship() {
    if (drinkStockQuantity <= 0) {
      throw new RuntimeException( "Insufficient Stock." );
    }
    drinkStockQuantity = drinkStockQuantity - 1;
  }

  public int getDrinkId() {
    return drinkId;
  }

  public String getDrinkName() {
    return drinkName;
  }

  public int getDrinkPrice() {
    return drinkPrice;
  }
}
