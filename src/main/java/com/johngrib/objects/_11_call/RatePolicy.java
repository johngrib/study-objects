package com.johngrib.objects._11_call;

import com.johngrib.objects._02_movie.Money;

public interface RatePolicy {
  Money calculateFee(Phone phone);
}
