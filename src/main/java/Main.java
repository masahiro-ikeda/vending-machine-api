import controler.OrderController;

public class Main {

  public static void main(String [] args) {
    System.out.println("bending machine start.");

    new OrderController().receive();

    System.out.println("bending machine finish.");
  }
}
