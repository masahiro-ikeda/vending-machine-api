package api.domain.valueobject;

/**
 * ドリンクの温度状態.
 */
public enum TemperatureState {
  HOT( "hot" ),
  COLD( "cold" );

  private final String type;

  TemperatureState(String type) {
    this.type = type;
  }

  public String getValue() {
    return this.type;
  }
}
