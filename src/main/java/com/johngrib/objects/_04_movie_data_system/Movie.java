package com.johngrib.objects._04_movie_data_system;

import com.johngrib.objects._02_movie.Money;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Movie {
  private String title;
  private Duration runningTime;
  @Getter
  @Setter
  private Money fee;

  @Setter
  private List<DiscountCondition> discountConditions;

  public List<DiscountCondition> getDiscountConditions() {
    return Collections.unmodifiableList(discountConditions);
  }

  @Getter
  @Setter
  private MovieType movieType;
  @Getter
  @Setter
  private Money discountAmount;
  @Getter
  @Setter
  private double discountPercent;

  public Money calculateAmountDiscountedFee() {
    if (movieType != MovieType.AMOUNT_DISCOUNT) {
      throw new IllegalArgumentException();
    }
    return fee.minus(discountAmount);
  }

  public Money calculatePercentDiscountedFee() {
    if (movieType != MovieType.PERCENT_DISCOUNT) {
      throw new IllegalArgumentException();
    }
    return fee.minus(fee.times(discountPercent));
  }

  public Money calculateNoneDiscountedFee() {
    if (movieType != MovieType.NONE_DISCOUNT) {
      throw new IllegalArgumentException();
    }
    return fee;
  }

  /**
   * 할인 조건을 하나씩 훑어 가면서 할인 조건의 타입을 체크한 다음, 할인 가능 여부를 리턴한다.
   *
   * @param whenScreened 상영일시
   * @param sequence     상영순번
   * @return 할인 가능하다면 true
   */
  public boolean isDiscountable(LocalDateTime whenScreened, int sequence) {
    for (DiscountCondition condition : discountConditions) {
      if (condition.getType() == DiscountConditionType.PERIOD) {
        if (condition.isDiscountable(whenScreened.getDayOfWeek(), whenScreened.toLocalTime())) {
          return true;
        }
      } else {
        if (condition.isDiscountable(sequence)) {
          return true;
        }
      }
    }
    return false;
  }
}
