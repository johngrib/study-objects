package com.johngrib.objects._04_movie_data_system;

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
