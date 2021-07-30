package api.infrastructure;

import api.application.repository.PaymentRepository;
import api.domain.model.payments.Payments;
import api.domain.model.payments.payment.Payment;
import api.domain.model.payments.payment.PaymentFactory;
import api.infrastructure.entity.PaymentEntity;
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
    List<Payment> paymentList = payments.paymentList();
    List<PaymentEntity> paymentEntities = paymentList.stream().map( payment ->
        new PaymentEntity(
            payment.paymentId().value(),
            payment.paymentAmount().value(),
            payment.paymentType().name(),
            payment.paymentAt()
        )
    ).collect( Collectors.toList() );
    paymentJpaRepository.saveAll( paymentEntities );
  }

  @Override
  public Payments fetch() {
    List<PaymentEntity> result = paymentJpaRepository.findAll();
    List<Payment> paymentList = result.stream().map( paymentEntity ->
        PaymentFactory.restore(
            paymentEntity.getPaymentId(),
            paymentEntity.getPaymentAmount(),
            paymentEntity.getPaymentType(),
            paymentEntity.getPaymentAt()
        )
    ).collect( Collectors.toList() );
    return new Payments( paymentList );
  }
}
