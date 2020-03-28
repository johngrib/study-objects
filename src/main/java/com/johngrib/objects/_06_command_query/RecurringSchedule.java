package com.johngrib.objects._06_command_query;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public class RecurringSchedule {
  private String subject;
  @Getter
  private DayOfWeek dayOfWeek;
  @Getter
  private LocalTime from;
  @Getter
  private Duration duration;

  public RecurringSchedule(String subject, DayOfWeek dayOfWeek, LocalTime from, Duration duration) {
    this.subject = subject;
    this.dayOfWeek = dayOfWeek;
    this.from = from;
    this.duration = duration;
  }
}
