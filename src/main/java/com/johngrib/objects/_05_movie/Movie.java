package com.johngrib.objects._05_movie;

import com.johngrib.objects._02_movie.Money;
import com.johngrib.objects._04_movie_data_system.MovieType;

import java.time.Duration;
import java.util.List;

public class Movie {
  private String title;
  private Duration runningTime;
  private Money fee;

  // 새로운 문제: 두 클래스와 결합이 생겨 전체적인 결합도가 높아졌다.
  private List<PeriodCondition> periodConditions;
  private List<SequenceCondition> sequenceConditions;

  private MovieType movieType;
  private Money discountAmount;
  private double discountPercent;

  public Money calculateMovieFee(Screening screening) {
    return null;
  }

  private boolean isDiscountable(Screening screening) {
    return checkPeriodConditions(screening) || checkSequenceConditions(screening);
  }

  private boolean checkPeriodConditions(Screening screening) {
    return periodConditions.stream()
            .anyMatch(condition -> condition.isSatisfiedBy(screening));
  }

  private boolean checkSequenceConditions(Screening screening) {
    return sequenceConditions.stream()
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
