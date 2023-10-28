package api.application.payment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentUsecaseTest {

  @Autowired
  PaymentUseCase paymentUseCase;

  @Test
  void execute() {
    // given
    var input = new PaymentInput(100);

    // when
    var output = paymentUseCase.execute(input);

    // then
    // 省略

  }
}
