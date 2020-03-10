package com.johngrib.objects._04_movie_data_system;

import lombok.Getter;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {
  @Getter
  private DiscountConditionType type;
  private int sequence;
  private DayOfWeek dayOfWeek;
  private LocalTime startTime;
  private LocalTime endTime;

  public boolean isDiscountable(DayOfWeek dayOfWeek, LocalTime time) {
    if (type != DiscountConditionType.PERIOD) {
      throw new IllegalArgumentException();
    }
    return this.dayOfWeek.equals(dayOfWeek)
            && this.startTime.compareTo(time) <= 0
            && this.endTime.compareTo(time) >= 0;
  }

  public boolean isDiscountable(int sequence) {
    if (type != DiscountConditionType.SEQUENCE) {
      throw new IllegalArgumentException();
    }
    return this.sequence == sequence;
  }
}
