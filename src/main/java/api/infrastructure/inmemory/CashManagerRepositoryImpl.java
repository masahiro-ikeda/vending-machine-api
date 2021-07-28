package api.infrastructure.inmemory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import api.application.repository.CashManagerRepository;
import api.domain.model.cash.CashStock;
import api.domain.model.cash.CashStocks;
import api.domain.valueobject.Quantity;
import api.domain.model.payment.YenCurrency;

@Component
public class CashManagerRepositoryImpl implements CashManagerRepository {

  private static List<CashStock> cashStockList = Arrays.asList(
      new CashStock( YenCurrency.TEN,          new Quantity( 30 ) ),
      new CashStock( YenCurrency.FIFTY,        new Quantity( 30 ) ),
      new CashStock( YenCurrency.HUNDRED,      new Quantity( 30 ) ),
      new CashStock( YenCurrency.FIVE_HUNDRED, new Quantity( 30 ) ),
      new CashStock( YenCurrency.THOUSAND,     new Quantity( 30 ) )
  );

  @Override
  public CashStocks fetch() {
    return new CashStocks( new ArrayList<>( cashStockList ) );
  }

  @Override
  public void store(CashStocks cashStocks) {
    cashStockList = cashStocks.getCashStockList();
  }
}
