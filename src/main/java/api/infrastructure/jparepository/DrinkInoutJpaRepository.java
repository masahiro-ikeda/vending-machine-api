package api.infrastructure.jparepository;

import api.infrastructure.entity.DrinkInoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkInoutJpaRepository extends JpaRepository<DrinkInoutEntity, String> {

  List<DrinkInoutEntity> findByDrinkId(String drinkId);
}
