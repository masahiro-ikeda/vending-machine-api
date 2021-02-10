package api.presentation.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 飲料の購入結果の画面表示モデル.
 */
@AllArgsConstructor
@Getter
public class PurchaseViewModel {
  private DrinkViewModel drink;
  private List<CashViewModel> changes;
}
