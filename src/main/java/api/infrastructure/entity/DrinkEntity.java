package api.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "drink")
@AllArgsConstructor
@NoArgsConstructor // JPA利用にデフォルトコンストラクタが必須なため
@Getter
public class DrinkEntity {
  @Id
  private String drinkId;
  private String drinkName;
  private int drinkPrice;
}
