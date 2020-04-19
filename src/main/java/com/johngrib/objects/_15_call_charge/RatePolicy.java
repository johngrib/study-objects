package com.johngrib.objects._15_call_charge;

import com.johngrib.objects._02_movie.Money;

public interface RatePolicy {
  Money calculateFee(Phone phone);
}
