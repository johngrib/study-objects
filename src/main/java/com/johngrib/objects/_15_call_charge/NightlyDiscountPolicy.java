package com.johngrib.objects._15_call_charge;

import com.johngrib.objects._02_movie.Money;

import java.time.Duration;

public class NightlyDiscountPolicy extends BasicRatePolicy {
  private static final int LATE_NIGHT_HOUR = 22;

  private Money nightlyAmount;
  private Money regularAmount;
  private Duration seconds;

  public NightlyDiscountPolicy(Money nightlyAmount, Money regularAmount, Duration seconds) {
    this.nightlyAmount = nightlyAmount;
    this.regularAmount = regularAmount;
    this.seconds = seconds;
  }

  protected Money calculateCallFee(Call call) {
    if (call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
      return nightlyAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
    }
    return regularAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
  }
}
