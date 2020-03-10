package com.johngrib.objects._04_movie_data_system;

import com.johngrib.objects._02_movie.Money;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
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
}
