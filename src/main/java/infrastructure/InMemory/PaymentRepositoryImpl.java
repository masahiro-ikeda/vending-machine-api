package infrastructure.InMemory;

import domain.model.payment.Payment;
import domain.model.payment.Payments;
import repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepositoryImpl implements PaymentRepository {

  // スレッドセーフではないけど...
  private static List<Payment> payments = new ArrayList<>();

  @Override
  public void store(Payments payments) {
    this.payments = payments.getPayments();
  }

  @Override
  public Payments fetch() {
    return new Payments( payments );
  }

  @Override
  public void release() {
    payments.clear();
  }
}
