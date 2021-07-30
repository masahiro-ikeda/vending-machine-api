package api.infrastructure.jparepository;

import api.infrastructure.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentEntity, String> {
}
