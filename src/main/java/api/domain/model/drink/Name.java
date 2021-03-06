package api.domain.model.drink;

/**
 * 名称.
 * (現状は飲料の名称のみ)
 */
public class Name {

  private final String name;

  // 最大字数
  private static final int MAX_LENGTH = 20;

  /**
   * コンスタラクタ.
   *
   * @param name 名称
   */
  public Name(String name) {
    if (name == null) {
      throw new IllegalArgumentException( "" );
    }
    if (name.length() > MAX_LENGTH) {
      throw new IllegalArgumentException( "" );
    }
    this.name = name;
  }

  public String value() {
    return name;
  }
}
