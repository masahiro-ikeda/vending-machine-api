package api.infrastructure;

import api.application.repository.DrinkRepository;
import api.domain.model.drink.Drink;
import api.domain.model.drink.DrinkId;
import api.domain.model.drink.DrinkPrice;
import api.domain.model.drink.inout.DrinkInout;
import api.domain.model.drink.inout.DrinkInoutId;
import api.domain.model.drink.inout.DrinkInoutQuantity;
import api.domain.model.drink.inout.DrinkInoutType;
import api.infrastructure.entity.DrinkInoutEntity;
import api.infrastructure.jparepository.DrinkInoutJpaRepository;
import api.infrastructure.jparepository.DrinkJpaRepository;
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
  public Drink fetchById(DrinkId drinkId) {

    var drinkEntity = drinkJpaRepository.getById( drinkId.value() );
    var drinkInoutEntityList = drinkInoutJpaRepository.findByDrinkId( drinkId.value() );

    var drinkInoutList = drinkInoutEntityList.stream().map( drinkInoutEntity ->
        new DrinkInout(
            new DrinkInoutId( drinkInoutEntity.getDrinkInoutId() ),
            new DrinkId( drinkInoutEntity.getDrinkId() ),
            DrinkInoutType.valueOf( drinkInoutEntity.getDrinkInoutType() ),
            new DrinkInoutQuantity( drinkInoutEntity.getDrinkInoutQuantity() ),
            drinkInoutEntity.getDrinkInoutAt()
        )
    ).collect( Collectors.toList() );

    return new Drink(
        new DrinkId( drinkEntity.getDrinkId() ),
        new DrinkPrice( drinkEntity.getDrinkPrice() ),
        drinkInoutList
    );
  }

  @Override
  public void store(Drink drink) {

    var drinkInoutList = drink.drinkInoutList();
    var drinkInoutEntityList = drinkInoutList.stream().map( drinkInout ->
        new DrinkInoutEntity(
            drinkInout.drinkInoutId().value(),
            drinkInout.drinkId().value(),
            drinkInout.drinkInoutType().name(),
            drinkInout.drinkInoutQuantity().value(),
            drinkInout.drinkInoutAt()
        ) ).collect( Collectors.toList() );

    drinkInoutJpaRepository.saveAll( drinkInoutEntityList );
  }
}
