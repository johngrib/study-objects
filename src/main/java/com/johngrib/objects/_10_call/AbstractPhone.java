package com.johngrib.objects._10_call;

import com.johngrib.objects._02_movie.Money;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPhone {

  /** 전체 통화 목록 */
  @Getter
  private List<Call> calls = new ArrayList<>();

  public Money calculateFee() {
    Money result = Money.ZERO;

    for (Call call : calls) {
      result = result.plus(calculateCallFee(call));
    }
    return result;
  }

  abstract protected Money calculateCallFee(Call call);
}
