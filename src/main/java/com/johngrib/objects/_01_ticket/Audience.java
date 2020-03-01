package com.johngrib.objects._01_ticket;

/** 관람객. */
public class Audience {
  private Bag bag;

  public Audience(Bag bag) {
    this.bag = bag;
  }

  public Long buy(Ticket ticket) {
    return bag.hold(ticket);
  }
}
