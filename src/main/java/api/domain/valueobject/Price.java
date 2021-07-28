package api.domain.valueobject;

/**
 * 値段クラス.
 */
public class Price {
  private final int value;

  /**
   * コンストラクタ.
   *
   * @param value
   */
  public Price(int value) {
    if (value < 1) {
      throw new IllegalArgumentException("Price Must Be More Than 1.");
    }
    this.value = value;
  }

  /**
   * 値段の額.
   *
   * @return
   */
  public int value() {
    return value;
  }
}
