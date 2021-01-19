package domain.model.drink;

public class DrinkDisplay {

  private int drinkId;
  private String drinkName;
  private int drinkPrice;
  private boolean isSaleable;

  /**
   * コンストラクタ.
   *
   * @param drinkId    ドリンクID
   * @param drinkName  ドリンク名
   * @param drinkPrice ドリンク価格
   * @param isSaleable 購入可能か？
   */
  public DrinkDisplay(int drinkId, String drinkName, int drinkPrice, boolean isSaleable) {
    this.drinkId = drinkId;
    this.drinkName = drinkName;
    this.drinkPrice = drinkPrice;
    this.isSaleable = isSaleable;
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

  public boolean isSaleable() {
    return isSaleable;
  }
}
