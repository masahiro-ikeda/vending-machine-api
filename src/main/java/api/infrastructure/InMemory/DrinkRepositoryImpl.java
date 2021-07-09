package api.infrastructure.InMemory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import api.application.repository.DrinkRepository;
import api.domain.model.drink.Drink;
import api.domain.model.drink.Price;
import api.domain.model.drink.Quantity;

@Component
public class DrinkRepositoryImpl implements DrinkRepository {

  // スレッドセーフではないけど...
  private static List<DrinkData> drinks = Arrays.asList(
      new DrinkData( 1, "Orange" , 120 , "cold", 5 ),
      new DrinkData( 2, "GreenTea" , 100 , "cold", 10 ),
      new DrinkData( 3, "Coffee" ,  150 , "hot", 3 ),
      new DrinkData( 4, "Cola" , 130 , "cold", 1 )
  );

  @Override
  public List<DrinkData> fetch() {
    // ディープコピーを返す
    return List.copyOf(drinks);
  }

  @Override
  public Drink fetchById(int drinkId) {
    Optional<DrinkData> target = drinks.stream().filter( dk -> dk.getDrinkId() == drinkId ).findFirst();
    if (target.isPresent()) {
      // ディープコピーを返す
      return new Drink(
          target.get().getDrinkId(),
          new Price(target.get().getDrinkPrice()),
          new Quantity(target.get().getDrinkQuantity())
      );
    } else {
      return null;
    }
  }
}
