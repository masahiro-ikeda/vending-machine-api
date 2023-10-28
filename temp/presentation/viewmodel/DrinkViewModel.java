package api.presentation.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 飲料の画面表示モデル.
 */
@AllArgsConstructor
@Getter
public class DrinkViewModel {
  private int drinkId;
  private String drinkName;
  private int drinkPrice;
  private String drinkTemperature;
  private boolean isSaleable;
}
