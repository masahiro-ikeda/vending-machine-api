package api.infrastructure.InMemory;

import api.domain.entity.drink.Drink;
import api.domain.valueobject.Name;
import api.domain.valueobject.money.Price;
import api.domain.valueobject.Quantity;
import api.domain.valueobject.TemperatureState;
import api.application.repository.DrinkRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class DrinkRepositoryImpl implements DrinkRepository {

  // スレッドセーフではないけど...
  private static List<Drink> drinks = Arrays.asList(
      new Drink( 1, new Name( "Orange" ), new Price( 120 ), TemperatureState.COLD, new Quantity( 5 ) ),
      new Drink( 2, new Name( "GreenTea" ), new Price( 100 ), TemperatureState.COLD, new Quantity( 10 ) ),
      new Drink( 3, new Name( "Coffee" ), new Price( 150 ), TemperatureState.HOT, new Quantity( 3 ) ),
      new Drink( 4, new Name( "Cola" ), new Price( 130 ), TemperatureState.COLD, new Quantity( 1 ) )
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
          target.get().getTemperatureState(),
          target.get().getDrinkQuantity()
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
