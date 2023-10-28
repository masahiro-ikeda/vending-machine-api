package api.infrastructure;

import api.domain.payments.PaymentRepository;
import api.domain.payments.Payment;
import api.domain.payments.Payments;
import api.infrastructure.jparepository.PaymentJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentRepositoryImpl implements PaymentRepository {

  private final PaymentJpaRepository paymentJpaRepository;

  public PaymentRepositoryImpl(PaymentJpaRepository paymentJpaRepository) {
    this.paymentJpaRepository = paymentJpaRepository;
  }

  @Override
  public void store(Payments payments) {
    List<PaymentDto> dtos = payments.payments().stream().map(PaymentDto::new).collect(Collectors.toList());
    paymentJpaRepository.saveAll(dtos);
  }

  @Override
  public void add(Payment payment) {
    paymentJpaRepository.save(new PaymentDto(payment));
  }

  @Override
  public Payments fetch() {
    List<PaymentDto> dtos = paymentJpaRepository.findAll();
    List<Payment> payments = dtos.stream().map(PaymentDto::toEntity).collect(Collectors.toList());
    return new Payments(payments);
  }
}
