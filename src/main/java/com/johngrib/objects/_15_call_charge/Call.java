package com.johngrib.objects._15_call_charge;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;

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
}
