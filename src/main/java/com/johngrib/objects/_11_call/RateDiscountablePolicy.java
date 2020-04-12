package com.johngrib.objects._11_call;

import com.johngrib.objects._02_movie.Money;

public class RateDiscountablePolicy extends AdditionalRatePolicy {
  private Money discountAmount;

  public RateDiscountablePolicy(Money discountAmount, RatePolicy next) {
    super(next);
    this.discountAmount = discountAmount;
  }

  @Override
  protected Money afterCalculated(Money fee) {
    return fee.minus(discountAmount);
  }
}
