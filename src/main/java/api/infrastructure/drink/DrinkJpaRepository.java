package api.infrastructure.drink;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkJpaRepository extends JpaRepository<DrinkDto, String> {
}
