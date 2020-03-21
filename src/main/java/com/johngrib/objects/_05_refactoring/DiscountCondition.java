package com.johngrib.objects._05_refactoring;

import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {
  @Getter
  @Setter
  private DiscountConditionType type;
  @Getter
  @Setter
  private int sequence;
  @Getter
  @Setter
  private DayOfWeek dayOfWeek;
  @Getter
  @Setter
  private LocalTime startTime;
  @Getter
  @Setter
  private LocalTime endTime;

  public boolean isDiscountable(Screening screening) {
    if (type == DiscountConditionType.PERIOD) {
      return isSatisfiedByPeriod(screening);
    }
    return isSatisfiedBySequence(screening);
  }

  private boolean isSatisfiedByPeriod(Screening screening) {
    return screening.getWhenScreened().getDayOfWeek().equals(dayOfWeek)
            && startTime.isBefore(screening.getWhenScreened().toLocalTime())
            && endTime.isAfter(screening.getWhenScreened().toLocalTime());
  }

  private boolean isSatisfiedBySequence(Screening screening) {
    return sequence == screening.getSequence();
  }
}
