package com.johngrib.objects._04_movie_data_system;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@DisplayName("DiscountCondition 클래스")
class DiscountConditionTest {
  final LocalTime given_할인_시작_시간 = LocalTime.of(9, 30);
  final LocalTime given_할인_종료_시간 = LocalTime.of(11, 30);
  final DayOfWeek given_할인_가능_요일 = DayOfWeek.TUESDAY;
  final int given_할인_가능_순번 = 42;

  List<DayOfWeek> given_할인_되지_않는_요일들() {
    return List.of(
            DayOfWeek.MONDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY
    );
  }


  @Nested
  @DisplayName("isDiscountable 메소드는")
  class Describe_isDiscountable {
    @Nested
    @DisplayName("DiscountConditionType 이 'PERIOD' 기준일 때")
    class Context_when_period_type {
      final DiscountConditionType givenType = DiscountConditionType.PERIOD;

      DiscountCondition givenDiscountCondition() {
        return new DiscountCondition(
                givenType,
                given_할인_가능_순번,
                given_할인_가능_요일,
                given_할인_시작_시간,
                given_할인_종료_시간
        );
      }

      boolean subject(DayOfWeek dayOfWeek, LocalTime time) {
        return givenDiscountCondition().isDiscountable(dayOfWeek, time);
      }

      @Nested
      @DisplayName("할인 조건에 맞는 시간이 주어지고")
      class Context_with_valid_time {
        List<LocalTime> given_times() {
          return List.of(
                  given_할인_시작_시간,
                  given_할인_시작_시간.plusSeconds(1),
                  given_할인_종료_시간.minusSeconds(1),
                  given_할인_종료_시간
          );
        }

        @Nested
        @DisplayName("할인 조건에 맞는 요일이 주어지면")
        class Context_with_valid_dayOfWeek {
          final DayOfWeek given_요일 = given_할인_가능_요일;

          @Test
          @DisplayName("true를 리턴한다")
          void it_returns_true() {
            for (LocalTime given_time : given_times()) {
              Assertions.assertTrue(subject(given_요일, given_time));
            }
          }
        }

        @Nested
        @DisplayName("할인 조건에 맞지 않는 요일이 주어지면")
        class Context_with_invalid_dayOfWeek {
          List<DayOfWeek> given_요일() {
            return given_할인_되지_않는_요일들();
          }

          @Test
          @DisplayName("false를 리턴한다")
          void it_returns_false() {
            for (LocalTime 할인_가능한_시간 : given_times()) {
              for (DayOfWeek 할인_조건에_맞지_않는_요일 : given_요일()) {
                Assertions.assertFalse(subject(할인_조건에_맞지_않는_요일, 할인_가능한_시간));
              }
            }
          }
        }
      }

      @Nested
      @DisplayName("할인 조건에 맞지 않는 시간이 주어지고")
      class Context_with_invalid_time {
        List<LocalTime> given_times() {
          return List.of(
                  given_할인_시작_시간.minusSeconds(1),
                  given_할인_종료_시간.plusSeconds(1)
          );
        }

        @Nested
        @DisplayName("할인 조건에 맞는 요일이 주어지면")
        class Context_with_valid_dayOfWeek {
          final DayOfWeek 할인_되는_요일 = given_할인_가능_요일;

          @Test
          @DisplayName("false를 리턴한다")
          void it_returns_false() {
            for (LocalTime 할인_되지_않는_시간 : given_times()) {
              Assertions.assertFalse(subject(할인_되는_요일, 할인_되지_않는_시간));
            }
          }
        }

        @Nested
        @DisplayName("할인 조건에 맞지 않는 요일이 주어지면")
        class Context_with_invalid_dayOfWeek {
          List<DayOfWeek> given_요일() {
            return given_할인_되지_않는_요일들();
          }

          @Test
          @DisplayName("false를 리턴한다")
          void it_returns_false() {
            for (LocalTime 할인_되지_않는_시간 : given_times()) {
              for (DayOfWeek 할인_조건에_맞지_않는_요일 : given_요일()) {
                Assertions.assertFalse(subject(할인_조건에_맞지_않는_요일, 할인_되지_않는_시간));
              }
            }
          }
        }
      }
    }

    @Nested
    @DisplayName("순번이 주어지면")
    class Context_with_sequence {
      @Test
      void it_occurs_an_exception() {
//          Assertions.assertThrows(() -> )
      }
    }
  }
}

