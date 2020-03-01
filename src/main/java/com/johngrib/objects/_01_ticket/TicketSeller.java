package com.johngrib.objects._01_ticket;

/** 티켓 판매원. */
public class TicketSeller {
  private TicketOffice ticketOffice;

  /** 티켓 판매원은 자신이 일하는 매표소를 알고 있어야 한다. */
  public TicketSeller(TicketOffice ticketOffice) {
    this.ticketOffice = ticketOffice;
  }

  public void sellTo(Audience audience) {
    if (audience.getBag().hasInvitation()) {
      // 초대장이 있는 관람객의 경우
      Ticket ticket = ticketOffice.getTicket();
      audience.getBag().setTicket(ticket);
    } else {
      // 초대장이 없는 관람객의 경우
      Ticket ticket = ticketOffice.getTicket();
      audience.getBag().minusAmount(ticket.getFee());
      ticketOffice.plusAmount(ticket.getFee());
      audience.getBag().setTicket(ticket);
    }
  }
}
