package com.johngrib.objects._04_movie_data_system;

import com.johngrib.objects._02_movie.Money;
import lombok.Getter;
import lombok.Setter;

public class Reservation {
  @Getter
  @Setter
  private Customer customer;
  @Getter
  @Setter
  private Screening screening;
  @Getter
  @Setter
  private Money fee;
  @Getter
  @Setter
  private int audienceCount;

  public Reservation(Customer customer, Screening screening, Money fee, int audienceCount) {
    this.customer = customer;
    this.screening = screening;
    this.fee = fee;
    this.audienceCount = audienceCount;
  }
}
