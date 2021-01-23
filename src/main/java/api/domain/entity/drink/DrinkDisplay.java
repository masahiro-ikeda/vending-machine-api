package api.domain.entity.drink;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DrinkDisplay {

  private int drinkId;
  private String drinkName;
  private int drinkPrice;
  private boolean isSaleable;
  private String drinkTemperature;
}
