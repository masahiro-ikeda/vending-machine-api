package api.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "drink_inout")
@AllArgsConstructor
@NoArgsConstructor // JPA利用にデフォルトコンストラクタが必須なため
@Getter
public class DrinkInoutEntity {
  @Id
  private String drinkInoutId;
  private String drinkId;
  private String drinkInoutType;
  private int drinkInoutQuantity;
  private LocalDateTime drinkInoutAt;
}
