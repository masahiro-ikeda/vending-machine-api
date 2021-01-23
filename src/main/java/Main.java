import controler.OrderController;

public class Main {

  public static void main(String[] args) {
    System.out.println( "vending machine start." );

    new OrderController().receive();

    System.out.println( "vending machine finish." );
  }
}
