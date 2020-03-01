package com.johngrib.objects._01_ticket;

import lombok.Getter;

/** 공연 관람 티켓. */
public class Ticket {
  @Getter
  private Long fee;

  public Ticket(Long fee) {
    this.fee = fee;
  }
}
