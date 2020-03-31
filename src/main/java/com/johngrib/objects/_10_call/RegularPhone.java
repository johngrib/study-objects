package com.johngrib.objects._10_call;

import com.johngrib.objects._02_movie.Money;
import lombok.Getter;

import java.time.Duration;

/**
 * 일반 요금제.
 */
public class RegularPhone extends Phone {
  /** 단위 요금. */
  @Getter
  private Money amount;
  @Getter
  private Duration seconds;

  public RegularPhone(Money amount, Duration seconds) {
    super(0);
    this.amount = amount;
    this.seconds = seconds;
  }

  public RegularPhone(Money amount, Duration seconds, double taxRate) {
    super(taxRate);
    this.amount = amount;
    this.seconds = seconds;
  }

  @Override
  protected Money calculateCallFee(Call call) {
    return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
  }
}
