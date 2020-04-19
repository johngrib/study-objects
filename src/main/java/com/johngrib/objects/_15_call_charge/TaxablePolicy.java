package com.johngrib.objects._15_call_charge;

import com.johngrib.objects._02_movie.Money;

public class TaxablePolicy extends AdditionalRatePolicy {
  private double taxRatio;

  public TaxablePolicy(double taxRatio, RatePolicy next) {
    super(next);
    taxRatio = taxRatio;
  }

  @Override
  protected Money afterCalculated(Money fee) {
    return fee.plus(fee.times(taxRatio));
  }
}
