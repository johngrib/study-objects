package com.johngrib.objects._15_call_charge;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 개별 통화 시간.
 */
public class Call {
  @Getter
  private LocalDateTime from;
  private LocalDateTime to;

  public Call(LocalDateTime from, LocalDateTime to) {
    this.from = from;
    this.to = to;
  }

  public Duration getDuration() {
    return Duration.between(from, to);
  }
}
