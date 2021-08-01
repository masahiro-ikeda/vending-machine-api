package api.domain.model.cash.inout;

import java.util.UUID;

public class CashInoutId {
  private final UUID cashInoutId;

  /**
   * 新規生成.
   */
  CashInoutId() {
    this.cashInoutId = UUID.randomUUID();
  }

  /**
   * 復元.
   *
   * @param cashInoutId 文字列のUUID
   */
  public CashInoutId(String cashInoutId) {
    this.cashInoutId = UUID.fromString( cashInoutId );
  }

  /**
   * @return 文字列の値
   */
  public String value() {
    return this.cashInoutId.toString();
  }
}
