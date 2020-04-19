package com.johngrib.objects._15_call_charge;

import com.johngrib.objects._02_movie.Money;

public abstract class BasicRatePolicy implements RatePolicy {
  @Override
  public Money calculateFee(Phone phone) {
    Money result = Money.ZERO;

    for (Call call : phone.getCalls()) {
      result.plus(calculateCallFee(call));
    }
    return result;
  }

  protected abstract Money calculateCallFee(Call call);
}
