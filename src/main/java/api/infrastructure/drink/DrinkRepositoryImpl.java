package api.infrastructure.drink;

import api.domain.drink.DrinkRepository;
import api.domain.drink.Drink;
import api.infrastructure.drink.DrinkInoutDto;
import api.infrastructure.drink.DrinkInoutJpaRepository;
import api.infrastructure.drink.DrinkJpaRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DrinkRepositoryImpl implements DrinkRepository {

  private final DrinkJpaRepository drinkJpaRepository;
  private final DrinkInoutJpaRepository drinkInoutJpaRepository;

  public DrinkRepositoryImpl(DrinkJpaRepository drinkJpaRepository, DrinkInoutJpaRepository drinkInoutJpaRepository) {
    this.drinkJpaRepository = drinkJpaRepository;
    this.drinkInoutJpaRepository = drinkInoutJpaRepository;
  }

  @Override
  public Drink fetchById(String drinkId) {
    var drinkDto = drinkJpaRepository.getById(drinkId);
    var drinkInoutDtos = drinkInoutJpaRepository.findByDrinkId(drinkId);
    return drinkDto.toEntity(drinkInoutDtos);
  }

  @Override
  public void store(Drink drink) {
    var drinkInoutDtos = drink.drinkInoutList().stream().map(DrinkInoutDto::new).collect(Collectors.toList());
    drinkInoutJpaRepository.saveAll(drinkInoutDtos);
  }
}
