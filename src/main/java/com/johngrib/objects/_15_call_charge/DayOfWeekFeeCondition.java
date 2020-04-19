package com.johngrib.objects._15_call_charge;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DayOfWeekFeeCondition implements FeeCondition {
  private List<DayOfWeek> dayOfWeeks = new ArrayList<>();

  public DayOfWeekFeeCondition(DayOfWeek... dayOfWeeks) {
    this.dayOfWeeks = Arrays.asList(dayOfWeeks);
  }

  @Override
  public List<DateTimeInterval> findTimeIntervals(Call call) {
    return call.getInterval()
            .splitByDay()
            .stream()
            .filter(each -> dayOfWeeks.contains(each.getFrom().getDayOfWeek()))
            .collect(Collectors.toList());
  }
}
