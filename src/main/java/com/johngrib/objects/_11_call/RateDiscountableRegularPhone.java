package com.johngrib.objects._11_call;

import com.johngrib.objects._02_movie.Money;

import java.time.Duration;

public class RateDiscountableRegularPhone extends RegularPhone {
  private Money discountAmount;

  public RateDiscountableRegularPhone(Money amount, Duration seconds, Money discountAmount) {
    super(amount, seconds);
    this.discountAmount = discountAmount;
  }

  @Override
  protected Money afterCalculated(Money fee) {
    return fee.minus(discountAmount);
  }
}
