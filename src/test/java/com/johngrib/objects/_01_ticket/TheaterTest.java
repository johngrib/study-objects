package com.johngrib.objects._01_ticket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Theater 클래스")
class TheaterTest {
  final Long givenTicketFee = 100L;

  List<Ticket> givenTickets() {
    List<Ticket> tickets = new ArrayList<>();
    tickets.add(new Ticket(givenTicketFee));
    return tickets;
  }

  TicketOffice given_매표소() {
    return new TicketOffice(1000000L, givenTickets());
  }

  TicketSeller given_티켓_판매원() {
    return new TicketSeller(given_매표소());
  }

  Theater given_극장() {
    return new Theater(given_티켓_판매원());
  }

  @Nested
  @DisplayName("enter 메소드는")
  class Describe_enter_method {

    @Nested
    @DisplayName("초대장이 있는 관람객이 주어지면")
    class Context_with_invitation {
      Invitation given_초대장 = new Invitation();

      Audience given_초대장_있는_관람객() {
        Bag bag = new Bag(100000L, given_초대장);
        return new Audience(bag);
      }

      @Test
      @DisplayName("(사이드 이펙트) 관람객의 가방에 티켓을 넣어준다")
      void it_puts_a_ticket_into_bag() {
        final Audience 관람객 = given_초대장_있는_관람객();
        final Theater 극장 = given_극장();
        final Long beforeMoney = 관람객.getBag().getAmount();
        극장.enter(관람객);

        Assertions.assertTrue(관람객.getBag().hasTicket(), "관람객의 가방에는 티켓이 들어있다");
        Assertions.assertEquals(beforeMoney, 관람객.getBag().getAmount(), "관람객의 돈은 늘지도 줄지도 않는다");
      }
    }

    @Nested
    @DisplayName("초대장이 없는 관람객이 주어지면")
    class Context_with_no_invitation {
      Audience given_초대장_없는_관람객() {
        Bag bag = new Bag(100000L);
        return new Audience(bag);
      }

      @Test
      @DisplayName("(사이드 이펙트) 관람객의 가방에 티켓을 넣어준다")
      void it_puts_a_ticket_into_bag() {
        final Audience 관람객 = given_초대장_없는_관람객();
        given_극장().enter(관람객);

        Assertions.assertTrue(관람객.getBag().hasTicket(), "관람객의 가방에는 티켓이 들어있다");
      }

      @Test
      @DisplayName("티켓 값 만큼의 가방 속 돈을 감소시킨다")
      void it_reduces_money_in_the_bag() {
        final Audience 관람객 = given_초대장_없는_관람객();
        final Long beforeMoney = 관람객.getBag().getAmount();
        given_극장().enter(관람객);

        final Long afterMoney = 관람객.getBag().getAmount();
        Assertions.assertTrue(beforeMoney - afterMoney == givenTicketFee, "관람객의 돈은 티켓 값 만큼 줄어든다");
      }
    }
  }
}
