package api.infrastructure.mysql;

import api.application.repository.PaymentRepository;
import api.domain.model.payment.Payment;
import api.domain.model.payment.Payments;
import api.domain.model.payment.PaymentType;
import api.domain.model.payment.YenCurrency;
import api.infrastructure.mysql.entity.PaymentEntity;
import api.infrastructure.mysql.repository.PaymentJpaRepository;
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
  public void store(Payment payment) {
    PaymentEntity paymentEntity = new PaymentEntity(
        payment.getPaymentId(),
        payment.getPaymentAmount().value(),
        payment.getPaymentType().name(),
        payment.getPaymentAt()
    );
    paymentJpaRepository.save( paymentEntity );
  }

  @Override
  public Payments fetch() {
    List<PaymentEntity> result = paymentJpaRepository.findAll();
    return new Payments( result.stream().map( it ->
        new Payment(
            it.getPaymentId(),
            YenCurrency.of( it.getPaymentAmount() ).amount(),
            PaymentType.valueOf( it.getPaymentType() ),
            it.getPaymentAt()
        )
    ).collect( Collectors.toList() ) );
  }
}
