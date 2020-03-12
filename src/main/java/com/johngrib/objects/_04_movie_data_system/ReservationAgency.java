package com.johngrib.objects._04_movie_data_system;

import com.johngrib.objects._02_movie.Money;

public class ReservationAgency {
  public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
    Money fee = screening.calculateFee(audienceCount);
    return new Reservation(customer, screening, fee, audienceCount);
  }
}
