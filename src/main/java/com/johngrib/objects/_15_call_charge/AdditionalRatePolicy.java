package com.johngrib.objects._15_call_charge;

import com.johngrib.objects._02_movie.Money;

public abstract class AdditionalRatePolicy implements RatePolicy {
  private RatePolicy next;

  public AdditionalRatePolicy(RatePolicy next) {
    this.next = next;
  }

  @Override
  public Money calculateFee(Phone phone) {
    Money fee = next.calculateFee(phone);
    return afterCalculated(fee);
  }

  protected abstract Money afterCalculated(Money fee);
}
