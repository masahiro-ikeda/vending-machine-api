package api.infrastructure.InMemory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import api.application.repository.CashManagerRepository;
import api.domain.entity.cash.Cash;
import api.domain.entity.cash.CashManager;
import api.domain.model.drink.Quantity;
import api.domain.model.payment.YenCurrency;

@Component
public class CashManagerRepositoryImpl implements CashManagerRepository {

  private static List<Cash> cashList = Arrays.asList(
      new Cash( YenCurrency.TEN,          new Quantity( 30 ) ),
      new Cash( YenCurrency.FIFTY,        new Quantity( 30 ) ),
      new Cash( YenCurrency.HUNDRED,      new Quantity( 30 ) ),
      new Cash( YenCurrency.FIVE_HUNDRED, new Quantity( 30 ) ),
      new Cash( YenCurrency.THOUSAND,     new Quantity( 30 ) )
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
