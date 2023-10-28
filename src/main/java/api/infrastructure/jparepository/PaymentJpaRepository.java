package api.infrastructure.jparepository;

import api.infrastructure.PaymentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentJpaRepository extends JpaRepository<PaymentDto, String> {
}
