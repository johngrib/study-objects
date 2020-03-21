package com.johngrib.objects._05_movie;

import com.johngrib.objects._02_movie.Money;

import java.time.Duration;

public class NoneDiscountMovie extends Movie {
  public NoneDiscountMovie(String title, Duration runningTime, Money fee) {
    super(title, runningTime, fee);
  }

  @Override
  protected Money calculateDiscountAmount() {
    return Money.ZERO;
  }
}
