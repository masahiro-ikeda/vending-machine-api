package api.infrastructure.drink;

import api.domain.drink.inout.DrinkInout;
import api.domain.drink.inout.DrinkInoutType;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "drink_inout")
@NoArgsConstructor // JPA利用にデフォルトコンストラクタが必須なため
public class DrinkInoutDto {

  @Id
  private String drinkInoutId;
  private String drinkId;
  private String drinkInoutType;
  private int drinkInoutQuantity;
  private LocalDateTime drinkInoutAt;

  /**
   * コンストラクタ.
   */
  DrinkInoutDto(DrinkInout inout) {
    this.drinkInoutId = inout.drinkInoutId();
    this.drinkId = inout.drinkId();
    this.drinkInoutType = inout.drinkInoutType().name();
    this.drinkInoutQuantity = inout.drinkInoutQuantity();
    this.drinkInoutAt = inout.drinkInoutAt();
  }

  /**
   * 復元.
   */
  DrinkInout toEntity() {
    return new DrinkInout(
        this.drinkInoutId,
        this.drinkId,
        DrinkInoutType.valueOf(this.drinkInoutType),
        this.drinkInoutQuantity,
        this.drinkInoutAt);
  }
}
