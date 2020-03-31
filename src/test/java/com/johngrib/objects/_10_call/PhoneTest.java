package com.johngrib.objects._10_call;

import com.johngrib.objects._02_movie.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Phone 클래스")
class PhoneTest {
  @Nested
  @DisplayName("calculateFee 메소드")
  class Describe_calculateFee {

    @Nested
    @DisplayName("10초에 5원씩 부과되는 요금제에 가입했고")
    class Context_with_10sec_5won_phone {
      Phone givenPhone() {
        return new Phone(Money.wons(5), Duration.ofSeconds(10));
      }

      @Nested
      @DisplayName("1분짜리 통화가 두 번 있었다면")
      class Context_with_2_calls {

        Call getFirstCall() {
          return new Call(
                  LocalDateTime.of(2018, 1, 1, 12, 10, 0),
                  LocalDateTime.of(2018, 1, 1, 12, 11, 0)
          );
        }

        Call getSecondCall() {
          return new Call(
                  LocalDateTime.of(2018, 1, 2, 12, 10, 0),
                  LocalDateTime.of(2018, 1, 2, 12, 11, 0)
          );
        }

        Phone givenUsedPhone() {
          final Phone phone = givenPhone();
          phone.call(getFirstCall());
          phone.call(getSecondCall());
          return phone;
        }

        @Test
        @DisplayName("60원을 리턴한다")
        void it_returns_calculated_won() {
          assertEquals(givenUsedPhone().calculateFee(), Money.wons(60.0));
        }
      }
    }
  }
}