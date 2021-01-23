package infrastructure.InMemory;

import domain.model.payment.Payment;
import domain.model.payment.PaymentHolder;
import repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

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
