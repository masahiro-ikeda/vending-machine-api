package api.domain.valueobject;

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
      throw new NullPointerException( "Name cannot null." );
    }
    if (name.length() > MAX_LENGTH) {
      throw new IllegalArgumentException( "Name too long." );
    }
    this.name = name;
  }

  public String value() {
    return name;
  }
}
