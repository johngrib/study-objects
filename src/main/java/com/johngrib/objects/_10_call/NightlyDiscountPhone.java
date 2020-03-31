package com.johngrib.objects._10_call;

import com.johngrib.objects._02_movie.Money;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * 심야 할인 요금제.
 */
public class NightlyDiscountPhone {
  private static final int LATE_NIGHT_HOUR = 22;

  private Money nightlyAmount;
  private Money regularAmount;
  private Duration seconds;
  private List<Call> calls = new ArrayList<>();
  private double taxRate;

  public NightlyDiscountPhone(Money nightlyAmount, Money regularAmount, Duration seconds) {
    this.nightlyAmount = nightlyAmount;
    this.regularAmount = regularAmount;
    this.seconds = seconds;
  }

  public NightlyDiscountPhone(Money nightlyAmount, Money regularAmount, Duration seconds, double taxRate) {
    this.nightlyAmount = nightlyAmount;
    this.regularAmount = regularAmount;
    this.seconds = seconds;
    this.taxRate = taxRate;
  }

  public Money calculateFee() {
    Money result = Money.ZERO;

    for (Call call : calls) {
      if (call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
        result = result.plus(
                nightlyAmount.times(call.getDuration().getSeconds() / seconds.getSeconds()));
      } else {
        result = result.plus(
                regularAmount.times(call.getDuration().getSeconds() / seconds.getSeconds()));
      }
    }
    // 일반 요금제는 plus 를 쓰는데 여기에서는 minus 를 쓴다.
    return result.minus(result.times(taxRate));
  }
}
