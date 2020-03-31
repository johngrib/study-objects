package com.johngrib.objects._10_call;

import com.johngrib.objects._02_movie.Money;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * 심야 할인 요금제.
 */
public class NightlyDiscountPhone extends Phone {
  private static final int LATE_NIGHT_HOUR = 22;

  private Money nightlyAmount;
  private List<Call> calls = new ArrayList<>();

  public NightlyDiscountPhone(Money nightlyAmount, Money regularAmount, Duration seconds) {
    super(regularAmount, seconds);
    this.nightlyAmount = nightlyAmount;
  }

  @Override
  public Money calculateFee() {
    // 부모 클래스의 calculateFee 호출
    Money result = super.calculateFee();

    Money nightlyFee = Money.ZERO;

    for (Call call : calls) {
      if (call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
        nightlyFee = nightlyFee.plus(getAmount().minus(nightlyAmount).times(
                call.getDuration().getSeconds() / getSeconds().getSeconds()
        ));
      }
    }
    // 일반 요금제는 plus 를 쓰는데 여기에서는 minus 를 쓴다.
    return result.minus(nightlyFee);
  }
}
