package api.infrastructure;

import api.domain.sale.SaleRepository;
import api.domain.sale.Sale;
import api.infrastructure.jparepository.SaleJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class SaleRepositoryImpl implements SaleRepository {

  private final SaleJpaRepository saleJpaRepository;

  public SaleRepositoryImpl(SaleJpaRepository saleJpaRepository) {
    this.saleJpaRepository = saleJpaRepository;
  }

  @Override
  public void store(Sale sale) {
    var dto = new SaleDto(sale);
    saleJpaRepository.save(dto);
  }
}
