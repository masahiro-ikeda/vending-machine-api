package api.infrastructure.jparepository;

import api.infrastructure.entity.CashInoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashInoutJpaRepository extends JpaRepository<CashInoutEntity, String> {
}
