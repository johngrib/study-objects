package com.johngrib.objects._15_call_charge;

import com.johngrib.objects._02_movie.Money;

import java.time.Duration;

public class FixedFeePolicy extends BasicRatePolicy {
  public Money amount;
  private Duration seconds;

  public FixedFeePolicy(Money amount, Duration seconds) {
    this.amount = amount;
    this.seconds = seconds;
  }

  @Override
  protected Money calculateCallFee(Call call) {
    return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
  }
}
