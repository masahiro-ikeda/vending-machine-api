package api.infrastructure;

import api.domain.drink.Drink;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "drink")
@NoArgsConstructor // JPA利用にデフォルトコンストラクタが必須なため
public class DrinkDto {

  @Id
  private String drinkId;
  private String drinkName;
  private int drinkPrice;

  Drink toEntity(List<DrinkInoutDto> inoutDtos) {
    return new Drink(
        this.drinkId,
        this.drinkPrice,
        inoutDtos.stream().map(DrinkInoutDto::toEntity).collect(Collectors.toList())
    );
  }
}
