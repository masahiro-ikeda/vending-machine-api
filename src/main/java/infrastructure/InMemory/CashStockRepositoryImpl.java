package infrastructure.InMemory;

import domain.model.cash.Cash;
import domain.model.cash.CashStock;
import repository.CashStockRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CashStockRepositoryImpl implements CashStockRepository {

  private static List<Cash> cashList = Arrays.asList(
      new Cash( 10, 30 ),
      new Cash( 50, 30 ),
      new Cash( 100, 30 ),
      new Cash( 500, 30 ),
      new Cash( 1000, 30 )
  );

  @Override
  public CashStock fetch() {
    return new CashStock( new ArrayList<>( cashList ) );
  }

  @Override
  public void store(CashStock cashStock) {
    cashList = cashStock.getCashList();
  }
}
