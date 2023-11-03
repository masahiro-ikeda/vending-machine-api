package api.infrastructure.drink;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkInoutJpaRepository extends JpaRepository<DrinkInoutDto, String> {

  List<DrinkInoutDto> findByDrinkId(String drinkId);
}
