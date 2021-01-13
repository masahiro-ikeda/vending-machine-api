package model;

import java.util.Arrays;

public class Coin {

  // int[]だとなぜかcontainsが機能しない
  private static Integer[] permitted = {10, 50, 100, 500, 1000};
  private int amount;

  Coin(int amount) {
    if (!Arrays.asList( permitted ).contains( amount )) {
      throw new IllegalArgumentException( "Not Permitted Coin." );
    }
    this.amount = amount;
  }

  int getAmount() {
    return amount;
  }
}
