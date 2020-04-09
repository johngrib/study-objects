package com.johngrib.objects._11_call;

import com.johngrib.objects._02_movie.Money;

import java.time.Duration;

public class TaxableRegularPhone extends RegularPhone {
  private double taxRate;

  public TaxableRegularPhone(Money amount, Duration seconds, double taxRate) {
    super(amount, seconds);
    this.taxRate = taxRate;
  }

  @Override
  public Money calculateFee() {
    Money fee = super.calculateFee();
    return fee.plus(fee.times(taxRate));
  }
}
