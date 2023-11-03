package api.infrastructure.cash;

import api.domain.cash.CashStockRepository;
import api.domain.cash.CashStocks;
import api.infrastructure.cash.CashInoutDto;
import api.infrastructure.cash.CashInoutJpaRepository;
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
    var dtos = cashInoutJpaRepository.findAll();
    var cashInoutList = dtos.stream().map(CashInoutDto::toEntity).collect(Collectors.toList());
    return new CashStocks(cashInoutList);
  }

  @Override
  public void store(CashStocks cashStocks) {
    var dtos = cashStocks.cashInoutList().stream().map(CashInoutDto::new).collect(Collectors.toList());
    cashInoutJpaRepository.saveAll(dtos);
  }
}
