package api.infrastructure;

import api.application.repository.SoldRepository;
import api.domain.model.purchase.sold.Sold;
import api.infrastructure.entity.DrinkSoldEntity;
import api.infrastructure.jparepository.SoldJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class SoldRepositoryImpl implements SoldRepository {

  private final SoldJpaRepository soldJpaRepository;

  public SoldRepositoryImpl(SoldJpaRepository soldJpaRepository) {
    this.soldJpaRepository = soldJpaRepository;
  }

  @Override
  public void store(Sold sold) {
    var soldEntity = new DrinkSoldEntity(
        sold.soldId().value(),
        sold.drinkId().value(),
        sold.soldQuantity().value(),
        sold.soldAt()
    );
    soldJpaRepository.save( soldEntity );
  }
}
