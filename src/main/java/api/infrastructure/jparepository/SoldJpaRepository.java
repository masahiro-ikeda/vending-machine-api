package api.infrastructure.jparepository;

import api.infrastructure.entity.DrinkSoldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldJpaRepository extends JpaRepository<DrinkSoldEntity, String> {


}
