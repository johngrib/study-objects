package com.johngrib.objects._04_movie_data_system;

import com.johngrib.objects._02_movie.Money;

public class ReservationAgency {
  /** Movie에 설정된 DiscountCondition 목록을 차례대로 탐색하며 영화의 할인 여부를 판단한다. */
  public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
    Money fee = screening.calculateFee(audienceCount);
    return new Reservation(customer, screening, fee, audienceCount);
  }
}
