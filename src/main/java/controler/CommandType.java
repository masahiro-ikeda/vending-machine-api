package controler;

public enum CommandType {

  PAYMENT( "payment" ),
  SELECT( "select" ),
  STOP( "stop" );

  private final String command;

  CommandType(String command) {
    this.command = command;
  }

  private String getValue() {
    return this.command;
  }

  public static CommandType getType(String command) {
    CommandType[] types = CommandType.values();
    for (CommandType type : types) {
      if (type.getValue().equals( command )) {
        return type;
      }
    }
    throw new IllegalArgumentException( "Illegal command." );
  }
}
