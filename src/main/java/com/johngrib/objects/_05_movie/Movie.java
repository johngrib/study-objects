package com.johngrib.objects._05_movie;

import com.johngrib.objects._02_movie.Money;
import com.johngrib.objects._04_movie_data_system.MovieType;

import java.time.Duration;
import java.util.List;

public class Movie {
  private String title;
  private Duration runningTime;
  private Money fee;

  private List<DiscountCondition> discountConditions;

  private MovieType movieType;
  private Money discountAmount;
  private double discountPercent;

  public Money calculateMovieFee(Screening screening) {
    if (isDiscountable(screening)) {
      return fee.minus(calculateDiscountAmount());
    }
    return fee;
  }

  private boolean isDiscountable(Screening screening) {
    return discountConditions.stream()
            .anyMatch(condition -> condition.isSatisfiedBy(screening));
  }

  private Money calculateDiscountAmount() {
    switch (movieType) {
      case AMOUNT_DISCOUNT:
        return calculateDiscountAmount();
      case PERCENT_DISCOUNT:
        return calculatePercentDiscountAmount();
      case NONE_DISCOUNT:
        return calculateNoneDiscountAmount();
    }
    throw new IllegalStateException();
  }

  private Money calculateAmountDiscountAmount() {
    return discountAmount;
  }

  private Money calculatePercentDiscountAmount() {
    return fee.times(discountPercent);
  }

  private Money calculateNoneDiscountAmount() {
    return Money.ZERO;
  }
}
