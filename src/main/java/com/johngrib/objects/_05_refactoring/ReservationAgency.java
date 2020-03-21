package com.johngrib.objects._05_refactoring;

import com.johngrib.objects._02_movie.Money;

import java.time.LocalTime;

public class ReservationAgency {
  /** Movie에 설정된 DiscountCondition 목록을 차례대로 탐색하며 영화의 할인 여부를 판단한다. */
  public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
    Movie movie = screening.getMovie();

    boolean discountable = false; // 할인 여부

    // 할인 가능 여부를 확인하는 for 문
    for (DiscountCondition condition : movie.getDiscountConditions()) {
      if (condition.getType() == DiscountConditionType.PERIOD) {
        // 기간 조건이면
        LocalTime whenScreened = screening.getWhenScreened().toLocalTime();

        discountable = screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek())
                && condition.getStartTime().compareTo(whenScreened) <= 0
                && condition.getEndTime().compareTo(whenScreened) >= 0;
      } else {
        // 순번 조건이면
        discountable = condition.getSequence() == screening.getSequence();
      }
      if (discountable) {
        break;
      }
    }

    // discountable 변수의 값을 체크하고 적절한 할인 정책에 따라 예매 요금을 계산하는 if 문
    Money fee;
    if (discountable) {
      Money discountAmount = Money.ZERO;
      switch (movie.getMovieType()) {
        case AMOUNT_DISCOUNT:
          // 금액 할인 정책인 경우
          discountAmount = movie.getDiscountAmount();
          break;
        case PERCENT_DISCOUNT:
          // 비율 할인 정책인 경우
          discountAmount = movie.getFee().times(movie.getDiscountPercent());
          break;
        case NONE_DISCOUNT:
          // 할인 정책이 적용되지 않은 경우
          discountAmount = Money.ZERO;
          break;
      }
      fee = movie.getFee().minus(discountAmount).times(audienceCount);
    } else {
      fee = movie.getFee();
    }
    return new Reservation(customer, screening, fee, audienceCount);
  }
}
