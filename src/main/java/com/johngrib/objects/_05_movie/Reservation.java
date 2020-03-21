package com.johngrib.objects._05_movie;

import com.johngrib.objects._02_movie.Money;
import com.johngrib.objects._04_movie_data_system.Customer;

public class Reservation {
  private Customer customer;
  private Screening screening;
  private Money fee;
  private int audienceCount;

  public Reservation(Customer customer, Screening screening, Money fee, int audienceCount) {
    this.customer = customer;
    this.screening = screening;
    this.fee = fee;
    this.audienceCount = audienceCount;
  }
}
