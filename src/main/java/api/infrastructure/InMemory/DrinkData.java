package api.infrastructure.InMemory;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DrinkData {

  private int drinkId;
  private String drinkName;
  private int drinkPrice;
  private String temperatureState;
  private int DrinkQuantity;
}