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
  /** 전체 통화 목록 */
  @Getter
  private List<Call> calls = new ArrayList<>();

  public Phone(Money amount, Duration seconds) {
    this.amount = amount;
    this.seconds = seconds;
  }

  public void call(Call call) {
    calls.add(call);
  }

  public Money calculateFee() {
    Money result = Money.ZERO;

    for (Call call : calls) {
      result = result.plus(calculateCallFee(call));
    }
    return result;
  }

  private Money calculateCallFee(Call call) {
    return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
  }
}
