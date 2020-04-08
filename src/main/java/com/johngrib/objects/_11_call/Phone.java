package com.johngrib.objects._11_call;

import com.johngrib.objects._02_movie.Money;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class Phone {

  private double taxRate;
  /** 전체 통화 목록 */
  @Getter
  private List<Call> calls = new ArrayList<>();

  public Phone(double taxRate) {
    this.taxRate = taxRate;
  }

  public Money calculateFee() {
    Money result = Money.ZERO;

    for (Call call : calls) {
      result = result.plus(calculateCallFee(call));
    }
    return result.plus(result.times(taxRate));
  }

  abstract protected Money calculateCallFee(Call call);
}
