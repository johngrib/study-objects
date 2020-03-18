package com.johngrib.objects._05_movie;

import com.johngrib.objects._04_movie_data_system.DiscountConditionType;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {
  private DiscountConditionType type;
  private int sequence;
  private DayOfWeek dayOfWeek;
  private LocalTime startTime;
  private LocalTime endTime;

  public boolean isSatisfiedBy(Screening screening) {
    if (type == DiscountConditionType.PERIOD) {
      return isSatisfiedByPeriod(screening);
    }
    return isSatisfiedByPeriod(screening);
  }

  private boolean isSatisfiedByPeriod(Screening screening) {
    return dayOfWeek.equals(screening.getWhenScreened().getDayOfWeek())
            && startTime.isBefore(screening.getWhenScreened().toLocalTime())
            && endTime.isAfter(screening.getWhenScreened().toLocalTime());
  }

  private boolean isSatisfiedBySequence(Screening screening) {
    return sequence == screening.getSequence();
  }
}
