package com.johngrib.objects._10_call;

import com.johngrib.objects._02_movie.Money;
import lombok.Getter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * 일반 요금제.
 */
public class Phone {
  /** 단위 요금. */
  @Getter
  private Money amount;
  @Getter
  private Duration seconds;
  /** 전체 통화 목록. */
  @Getter
  private List<Call> calls = new ArrayList<>();
  /** 세율. */
  private double taxRate;

  public Phone(Money amount, Duration seconds) {
    this.amount = amount;
    this.seconds = seconds;
  }

  public Phone(Money amount, Duration seconds, double taxRate) {
    this.amount = amount;
    this.seconds = seconds;
    this.taxRate = taxRate;
  }

  public void call(Call call) {
    calls.add(call);
  }

  public Money calculateFee() {
    Money result = Money.ZERO;

    for (Call call : calls) {
      result = result.plus(amount.times(call.getDuration().getSeconds() / seconds.getSeconds()));
    }
    return result.plus(result.times(taxRate));
  }
}
