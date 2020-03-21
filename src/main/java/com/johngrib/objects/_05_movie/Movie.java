package com.johngrib.objects._05_movie;

import com.johngrib.objects._02_movie.Money;
import com.johngrib.objects._04_movie_data_system.MovieType;
import lombok.Getter;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public abstract class Movie {
  private String title;
  private Duration runningTime;
  @Getter
  private Money fee;

  private List<DiscountCondition> discountConditions;

  private MovieType movieType;
  private Money discountAmount;
  private double discountPercent;

  public Movie(String title, Duration runningTime, Money fee, DiscountCondition... discountConditions) {
    this.title = title;
    this.runningTime = runningTime;
    this.fee = fee;
    this.discountConditions = Arrays.asList(discountConditions);
  }

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

  abstract protected Money calculateDiscountAmount();
}
