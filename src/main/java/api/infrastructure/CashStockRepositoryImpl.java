package api.infrastructure;

import api.application.repository.CashStockRepository;
import api.domain.model.cash.CashStocks;
import api.domain.model.cash.inout.CashInoutFactory;
import api.infrastructure.entity.CashInoutEntity;
import api.infrastructure.jparepository.CashInoutJpaRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CashStockRepositoryImpl implements CashStockRepository {

  private final CashInoutJpaRepository cashInoutJpaRepository;

  public CashStockRepositoryImpl(CashInoutJpaRepository cashInoutJpaRepository) {
    this.cashInoutJpaRepository = cashInoutJpaRepository;
  }

  @Override
  public CashStocks fetch() {
    var result = cashInoutJpaRepository.findAll();
    var cashInoutList = result.stream().map( cashInoutEntity ->
        CashInoutFactory.restore(
            cashInoutEntity.getCashInoutId(),
            cashInoutEntity.getCashCurrency(),
            cashInoutEntity.getCashInoutType(),
            cashInoutEntity.getCashInoutQuantity(),
            cashInoutEntity.getCashInoutAt()
        )
    ).collect( Collectors.toList() );

    return new CashStocks( cashInoutList );
  }

  @Override
  public void store(CashStocks cashStocks) {
    var cashInoutList = cashStocks.cashInoutList();
    var cashInoutEntityList = cashInoutList.stream().map( cashInout ->
        new CashInoutEntity(
            cashInout.cashInoutId().value(),
            cashInout.yenCurrency().value(),
            cashInout.cashInoutType().name(),
            cashInout.cashInoutQuantity().value(),
            cashInout.cashInoutAt()
        ) ).collect( Collectors.toList() );
    cashInoutJpaRepository.saveAll( cashInoutEntityList );
  }
}
