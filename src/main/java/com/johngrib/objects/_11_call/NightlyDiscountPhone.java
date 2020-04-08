package com.johngrib.objects._11_call;

import com.johngrib.objects._02_movie.Money;

import java.time.Duration;

/**
 * 심야 할인 요금제.
 */
public class NightlyDiscountPhone extends Phone {
  private static final int LATE_NIGHT_HOUR = 22;

  private Money nightlyAmount;
  private Money regularAmount;
  private Duration seconds;

  public NightlyDiscountPhone(Money nightlyAmount, Money regularAmount, Duration seconds) {
    super(0);
    this.nightlyAmount = nightlyAmount;
    this.regularAmount = regularAmount;
    this.seconds = seconds;
  }

  public NightlyDiscountPhone(Money nightlyAmount, Money regularAmount, Duration seconds, double taxRate) {
    super(taxRate);
    this.nightlyAmount = nightlyAmount;
    this.regularAmount = regularAmount;
    this.seconds = seconds;
  }

  @Override
  protected Money calculateCallFee(Call call) {
    if (call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
      return nightlyAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
    }
    return regularAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
  }

  @Override
  protected Money afterCalculated(Money fee) {
    return fee;
  }
}
