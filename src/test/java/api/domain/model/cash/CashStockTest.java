package api.domain.model.cash;

import api.domain.model.YenCurrency;
import api.domain.model.cash.inout.CashInout;
import api.domain.model.cash.inout.CashInoutFactory;
import api.domain.model.cash.inout.CashInoutType;
import api.domain.model.payments.ReturnAmount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@DisplayName("CashStockモデルのユニットテスト")
public class CashStockTest {

  @DisplayName("Nullで初期化")
  @Test
  void testInitializeNull() {
    Assertions.assertThrows( IllegalArgumentException.class, () -> new CashStock( null ) );
  }

  @DisplayName("空リストで初期化")
  @Test
  void testInitializeEmptyList() {
    var cashStock = new CashStock( new ArrayList<>() );
    Assertions.assertEquals( 0, cashStock.cashTotalAmount().value() );
  }

  /**
   * テスト用の入出金一覧.
   */
  private final List<CashInout> testCashInoutList = new ArrayList() {
    {
      add( CashInoutFactory.newIn( YenCurrency.TEN ) );
      add( CashInoutFactory.newIn( YenCurrency.FIFTY ) );
      add( CashInoutFactory.newIn( YenCurrency.HUNDRED ) );
      add( CashInoutFactory.newIn( YenCurrency.FIVE_HUNDRED ) );
      add( CashInoutFactory.newIn( YenCurrency.THOUSAND ) );
    }
  };

  @DisplayName("入出金記録で初期化")
  @Test
  void testInitializeCashInoutList() {
    var cashStock = new CashStock( testCashInoutList );

    // チェック
    Assertions.assertEquals( 1660, cashStock.cashTotalAmount().value() );
    Assertions.assertEquals( 5, cashStock.cashInoutList().size() );
  }

  @DisplayName("入金を実施")
  @Test
  void testInCash() {
    var cashStock = new CashStock( testCashInoutList );
    cashStock.inCash( YenCurrency.TEN );

    // チェック
    Assertions.assertEquals( 1670, cashStock.cashTotalAmount().value() );
    var targetInout = cashStock.cashInoutList().get( 5 );
    Assertions.assertEquals( YenCurrency.TEN, targetInout.yenCurrency() );
    Assertions.assertEquals( CashInoutType.IN, targetInout.cashInoutType() );
    Assertions.assertEquals( 1, targetInout.cashInoutQuantity().value() );
  }

  @DisplayName("出金を実施")
  @Test
  void testOutCash() {
    var cashStock = new CashStock( testCashInoutList );
    cashStock.outCash( new ReturnAmount( 1660 ) );

    // チェック
    Assertions.assertEquals( 0, cashStock.cashTotalAmount().value() );
    Assertions.assertEquals( 10, cashStock.cashInoutList().size() );
  }

  @DisplayName("出金しきれない場合")
  @Test
  void testOutOverCash() {
    var cashStock = new CashStock( testCashInoutList );

    // チェック
    Assertions.assertThrows( RuntimeException.class, () -> cashStock.outCash( new ReturnAmount( 1670 ) ) );
  }
}
