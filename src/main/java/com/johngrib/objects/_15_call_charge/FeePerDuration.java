package com.johngrib.objects._15_call_charge;

import com.johngrib.objects._02_movie.Money;

import java.time.Duration;

public class FeePerDuration {
  private Money fee;
  private Duration duration;

  public FeePerDuration(Money fee, Duration duration) {
    this.fee = fee;
    this.duration = duration;
  }

  public Money calculate(DateTimeInterval interval) {
    return fee.times(interval.duration().getSeconds() / duration.getSeconds());
  }
}
