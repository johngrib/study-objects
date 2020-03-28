package com.johngrib.objects._06_command_query;

import java.time.Duration;
import java.time.LocalDateTime;

public class Event {
  private String subject;
  private LocalDateTime from;
  private Duration duration;

  public Event(String subject, LocalDateTime from, Duration duration) {
    this.subject = subject;
    this.from = from;
    this.duration = duration;
  }

  public boolean isSatisfied(RecurringSchedule schedule) {
    if (from.getDayOfWeek() != schedule.getDayOfWeek()
            || !from.toLocalDate().equals(schedule.getFrom())) {
      reschedule(schedule);
      return false;
    }
    return true;
  }

  private void reschedule(RecurringSchedule schedule) {
    from = LocalDateTime.of(from.toLocalDate().plusDays(daysDistance(schedule)), schedule.getFrom());
    duration = schedule.getDuration();
  }

  private long daysDistance(RecurringSchedule schedule) {
    return schedule.getDayOfWeek().getValue() - from.getDayOfWeek().getValue();
  }
}
