package api.presentation.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * 返金結果の画面表示モデル.
 */
@AllArgsConstructor
@Getter
public class RepaymentViewModel {
  private List<CashViewModel> repayments;
}
