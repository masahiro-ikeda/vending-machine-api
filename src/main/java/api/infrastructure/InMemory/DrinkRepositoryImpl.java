package api.infrastructure.InMemory;

import api.domain.entity.drink.Drink;
import api.domain.enumeration.DrinkTemperatureType;
import api.repository.DrinkRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class DrinkRepositoryImpl implements DrinkRepository {

  // スレッドセーフではないけど...
  private static List<Drink> drinks = Arrays.asList(
      new Drink( 1, "Orange", 120, 10, DrinkTemperatureType.COLD ),
      new Drink( 2, "GreenTea", 100, 15, DrinkTemperatureType.COLD ),
      new Drink( 3, "Coffee", 150, 3, DrinkTemperatureType.HOT ),
      new Drink( 4, "Cola", 130, 1, DrinkTemperatureType.COLD )
  );

  @Override
  public List<Drink> fetch() {
    // ディープコピーを返す
    return new ArrayList<>( drinks );
  }

  @Override
  public Drink fetchById(int drinkId) {
    Optional<Drink> target = drinks.stream().filter( dk -> dk.getDrinkId() == drinkId ).findFirst();
    if (target.isPresent()) {
      // ディープコピーを返す
      return new Drink(
          target.get().getDrinkId(),
          target.get().getDrinkName(),
          target.get().getDrinkPrice(),
          target.get().getDrinkStockQuantity(),
          target.get().getDrinkTemperatureType()
      );
    } else {
      return null;
    }
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
