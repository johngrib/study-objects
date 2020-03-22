package com.johngrib.objects._06_refactoring_01;

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
    if (audience.getBag().hasInvitation()) {
      // 초대장이 있는 관람객의 경우
      Ticket ticket = ticketSeller.getTicketOffice().getTicket();
      audience.getBag().setTicket(ticket);
    } else {
      // 초대장이 없는 관람객의 경우
      Ticket ticket = ticketSeller.getTicketOffice().getTicket();
      audience.getBag().minusAmount(ticket.getFee());
      ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
      audience.getBag().setTicket(ticket);
    }
  }
}
