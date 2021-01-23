package api.infrastructure.InMemory;

import api.domain.entity.payment.Payment;
import api.domain.entity.payment.PaymentHolder;
import api.repository.PaymentRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentRepositoryImpl implements PaymentRepository {

  // スレッドセーフではないけど...
  private static List<Payment> payments = new ArrayList<>();

  @Override
  public void store(PaymentHolder paymentHolder) {
    payments = paymentHolder.getPayments();
  }

  @Override
  public PaymentHolder fetch() {
    // ディープコピーを返す
    return new PaymentHolder( new ArrayList<>( payments ) );
  }
}
