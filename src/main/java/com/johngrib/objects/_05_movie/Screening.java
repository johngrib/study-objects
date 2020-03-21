package com.johngrib.objects._05_movie;

import com.johngrib.objects._02_movie.Money;
import com.johngrib.objects._04_movie_data_system.Customer;
import lombok.Getter;

import java.time.LocalDateTime;

public class Screening {
  private Movie movie;
  @Getter
  private int sequence;
  @Getter
  private LocalDateTime whenScreened;

  public Reservation reserve(Customer customer, int audienceCount) {
    return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
  }

  private Money calculateFee(int audienceCount) {
    return movie.calculateMovieFee(this).times(audienceCount);
  }
}
