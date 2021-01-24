package api.presentation.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 自動販売機の画面表示モデル.
 */
@AllArgsConstructor
@Getter
public class VendingMachineViewModel {
  // 陳列する飲料一覧
  private final List<DrinkViewModel> drinks;
  // 支払い済みの金額
  private final int paymentAmount;
}
