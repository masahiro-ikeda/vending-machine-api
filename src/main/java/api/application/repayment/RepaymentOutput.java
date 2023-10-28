package api.application.repayment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class RepaymentOutput {
  Map<Integer, Integer> repays;
}
