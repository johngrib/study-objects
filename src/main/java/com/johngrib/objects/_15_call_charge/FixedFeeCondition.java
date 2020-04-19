package com.johngrib.objects._15_call_charge;

import java.util.Arrays;
import java.util.List;

public class FixedFeeCondition implements FeeCondition {
  @Override
  public List<DateTimeInterval> findTimeIntervals(Call call) {
    return Arrays.asList(call.getInterval());
  }
}
