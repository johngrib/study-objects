package com.johngrib.objects._15_call_charge;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 개별 통화 시간.
 */
public class Call {
  @Getter
  private DateTimeInterval interval;

  public Call(LocalDateTime from, LocalDateTime to) {
    this.interval = DateTimeInterval.of(from, to);
  }

  public Duration getDuration() {
    return interval.duration();
  }

  public LocalDateTime getFrom() {
    return interval.getFrom();
  }

  public LocalDateTime getTo() {
    return interval.getTo();
  }

  public List<DateTimeInterval> splitByDay() {
    return interval.splitByDay();
  }
}
