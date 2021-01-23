package infrastructure.InMemory;

import domain.model.drink.Drink;
import repository.DrinkRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DrinkRepositoryImpl implements DrinkRepository {

  // スレッドセーフではないけど...
  private static List<Drink> drinks = Arrays.asList(
      new Drink( 1, "Orange", 120, 10 ),
      new Drink( 2, "GreenTea", 100, 15 ),
      new Drink( 3, "Coffee", 150, 3 ),
      new Drink( 4, "Cola", 130, 1 )
  );

  @Override
  public List<Drink> fetch() {
    return drinks;
  }

  @Override
  public Drink fetchById(int drinkId) {
    Optional<Drink> target = drinks.stream().filter( dk -> dk.getDrinkId() == drinkId ).findFirst();
    return target.orElse( null );
  }

  @Override
  public void store(Drink stored) {
    for (Drink drink : drinks) {
      if (drink.getDrinkId() == stored.getDrinkId()) {
        drink = stored;
        return;
      }
    }
    drinks.add( stored );
  }
}
