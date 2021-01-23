package api.domain.enumeration;

/**
 * ドリンクの温度を列挙.
 */
public enum DrinkTemperatureType {
  HOT( "hot" ),
  COLD( "cold" );

  private final String type;

  DrinkTemperatureType(String type) {
    this.type = type;
  }

  public String getValue() {
    return this.type;
  }

  public static DrinkTemperatureType getType(String type) {
    for (DrinkTemperatureType drinkTemperatureType : DrinkTemperatureType.values()) {
      if (drinkTemperatureType.getValue() == type) {
        return drinkTemperatureType;
      }
    }
    return null;
  }
}
