package api.infrastructure.InMemory;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import api.application.repository.PaymentRepository;
import api.domain.model.payment.Payment;
import api.domain.model.payment.Payments;

@Component
public class PaymentRepositoryImpl implements PaymentRepository {

  // スレッドセーフではないけど...
  private static List<Payment> payments = new ArrayList<>();

  @Override
  public void store(Payment payment) {
    payments.add(payment);
  }

  @Override
  public Payments fetch() {
    // ディープコピーを返す
    return new Payments( List.copyOf(payments) );
  }
}
