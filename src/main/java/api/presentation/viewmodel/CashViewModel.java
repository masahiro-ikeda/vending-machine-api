package api.presentation.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 現金の画面表示モデル.
 * (返金、おつり表示用を想定)
 */
@AllArgsConstructor
@Getter
public class CashViewModel {
  private int amount;
  private int quantity;
}
