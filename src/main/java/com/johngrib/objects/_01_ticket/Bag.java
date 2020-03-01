package com.johngrib.objects._01_ticket;

/** 관람객이 소지품을 보관할 가방. */
public class Bag {
  private Long amount;
  private Invitation invitation;
  private Ticket ticket;

  /** 관람객이 초대장을 갖고 있다면 true 를 리턴한다. */
  public boolean hasInvitation() {
    return invitation != null;
  }

  /** 관람객이 티켓을 갖고 있다면 true 를 리턴한다. */
  public boolean hasTicket() {
    return ticket != null;
  }

  public void setTicket(Ticket ticket) {
    this.ticket = ticket;
  }

  /** 현금을 감소시킨다. */
  public void minusAmount(Long amount) {
    this.amount -= amount;
  }

  /** 현금을 증가시킨다. */
  public void plusAmount(Long amount) {
    this.amount += amount;
  }
}
