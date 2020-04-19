package com.johngrib.objects._15_call_charge;

import java.util.List;

public interface FeeCondition {
  List<DateTimeInterval> findTimeIntervals(Call call);
}
