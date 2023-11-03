package api.infrastructure.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleJpaRepository extends JpaRepository<SaleDto, String> {
}
