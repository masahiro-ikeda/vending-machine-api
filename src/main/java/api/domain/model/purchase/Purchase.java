package api.domain.model.purchase;

import api.domain.model.drink.Drink;
import api.domain.model.drink.DrinkQuantity;
import api.domain.model.payments.Payments;
import api.domain.model.payments.ReturnAmount;
import api.domain.model.purchase.sold.Sold;
import api.domain.model.purchase.sold.SoldQuantity;

public class Purchase {
  private final Drink drink;
  private final Payments payments;
  private Sold sold;

  /**
   * コンストラクタ.
   *
   * @param payments
   * @param drink
   */
  public Purchase(Drink drink, Payments payments) {
    this.drink = drink;
    this.payments = payments;
  }

  /**
   * 購入処理.
   */
  public ReturnAmount buy() {

    DrinkQuantity purchaseQuantity = new DrinkQuantity( 1 );

    // 在庫チェック
    if (!drink.drinkQuantity().canShip( purchaseQuantity )) {
      throw new RuntimeException( "Not Enough Stock." );
    }
    // 価格チェック
    if (drink.drinkPrice().value() > payments.paymentTotalAmount().value()) {
      throw new RuntimeException( "Not Enough Payment." );
    }

    // 購入記録の作成
    this.sold = new Sold( drink.drinkId(), new SoldQuantity( 1 ) );
    // ドリンク出庫記録の作成
    drink.out( purchaseQuantity );
    // 支払記録の作成
    payments.purchase( drink.drinkPrice() );
    // おつり記録の作成
    ReturnAmount changeAmount = payments.change();

    return changeAmount;
  }

  public Sold sold() {
    return this.sold;
  }
}
