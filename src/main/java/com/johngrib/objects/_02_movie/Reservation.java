package com.johngrib.objects._02_movie;

/** 예매 정보. */
public class Reservation {
  /** 고객. */
  private Customer customer;
  /** 상영 정보. */
  private Screening screening;
  /** 예매 요금. */
  private Money fee;
  /** 인원 수. */
  private int audienceCount;

  public Reservation(Customer customer, Screening screening, Money fee, int audienceCount) {
    this.customer = customer;
    this.screening = screening;
    this.fee = fee;
    this.audienceCount = audienceCount;
  }
}
