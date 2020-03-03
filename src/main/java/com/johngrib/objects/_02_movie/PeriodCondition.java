package com.johngrib.objects._02_movie;

import java.time.DayOfWeek;
import java.time.LocalTime;

/** 상영 기간 기준 할인 조건. */
public class PeriodCondition implements DiscountCondition {
  /** 요일 */
  private DayOfWeek dayOfWeek;
  private LocalTime startTime;
  private LocalTime endTime;

  public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
    this.dayOfWeek = dayOfWeek;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  /**
   * 상영 시작 시간이 기간 안에 포함된다면 true 를 리턴한다.
   *
   * @param screening 상영 정보
   * @return 할인 기준을 만족하면 true.
   */
  @Override
  public boolean isSatisfiedBy(Screening screening) {
    return screening.getStartTime().getDayOfWeek().equals(dayOfWeek)
            && startTime.compareTo(screening.getStartTime().toLocalTime()) <= 0
            && endTime.compareTo(screening.getStartTime().toLocalTime()) >= 0;
  }
}
