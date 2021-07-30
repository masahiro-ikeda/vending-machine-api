package api.domain.model.purchase.sold;

import java.util.UUID;

public class SoldId {
  private final UUID soldId;

  /**
   * 新規生成.
   */
  public SoldId() {
    this.soldId = UUID.randomUUID();
  }

  /**
   * @return 文字列の値
   */
  public String value() {
    return this.soldId.toString();
  }
}
