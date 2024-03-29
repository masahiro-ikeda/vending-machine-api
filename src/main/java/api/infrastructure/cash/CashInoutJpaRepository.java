package api.infrastructure.cash;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashInoutJpaRepository extends JpaRepository<CashInoutDto, String> {
}
