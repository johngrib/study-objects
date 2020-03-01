package com.johngrib.objects._01_ticket;

/** 극장. */
public class Theater {
  private TicketSeller ticketSeller;

  public Theater(TicketSeller ticketSeller) {
    this.ticketSeller = ticketSeller;
  }

  /**
   * 관람객을 맞이한다.
   *
   * @param audience 관람객
   */
  public void enter(Audience audience) {
    ticketSeller.sellTo(audience);
  }
}
