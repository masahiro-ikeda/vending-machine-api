package model;

public class Drink {
  private int id;
  private String name;
  private int price;
  private int stockQuantity;

  public Drink(int id, String name, int price, int stockQuantity) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.stockQuantity = stockQuantity;
  }
}
