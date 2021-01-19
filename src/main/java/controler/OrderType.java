package controler;

/**
 * 実行できる命令タイプを定義.
 */
public enum OrderType {

  PAY( "pay" ),
  BUY( "buy" ),
  REPAY( "repay" );

  private final String command;

  OrderType(String command) {
    this.command = command;
  }

  public String getValue() {
    return this.command;
  }
}
