import controler.OrderReceiver;

public class Main {

  public static void main(String [] args) {
    System.out.println("bending machine start.");

    new OrderReceiver().receive();

    System.out.println("bending machine finish.");
  }
}
