package com.johngrib.objects._01_ticket;

/** 티켓 판매원. */
public class TicketSeller {
  private TicketOffice ticketOffice;

  /** 티켓 판매원은 자신이 일하는 매표소를 알고 있어야 한다. */
  public TicketSeller(TicketOffice ticketOffice) {
    this.ticketOffice = ticketOffice;
  }

  /** 티켓을 관람객에게 판매한다. */
  public void sellTo(Audience audience) {
    ticketOffice.sellTicketTo(audience);
  }
}
