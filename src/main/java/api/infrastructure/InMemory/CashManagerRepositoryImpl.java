package api.infrastructure.InMemory;

import api.domain.entity.cash.Cash;
import api.domain.entity.cash.CashManager;
import api.domain.valueobject.Money;
import api.domain.valueobject.Quantity;
import api.domain.repository.CashManagerRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CashManagerRepositoryImpl implements CashManagerRepository {

  private static List<Cash> cashList = Arrays.asList(
      new Cash( Money.TEN,          new Quantity( 30 ) ),
      new Cash( Money.FIFTY,        new Quantity( 30 ) ),
      new Cash( Money.HUNDRED,      new Quantity( 30 ) ),
      new Cash( Money.FIVE_HUNDRED, new Quantity( 30 ) ),
      new Cash( Money.THOUSAND,     new Quantity( 30 ) )
  );

  @Override
  public CashManager fetch() {
    return new CashManager( new ArrayList<>( cashList ) );
  }

  @Override
  public void store(CashManager cashManager) {
    cashList = cashManager.getCashList();
  }
}