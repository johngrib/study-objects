package com.johngrib.objects._04_movie_data_system;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings({"InnerClassMayBeStatic", "NonAsciiCharacters"})
@DisplayName("DiscountCondition 클래스")
class DiscountConditionTest {
  private final LocalTime given_할인_시작_시간 = LocalTime.of(9, 30);
  private final LocalTime given_할인_종료_시간 = LocalTime.of(11, 30);
  private final DayOfWeek given_할인_가능_요일 = DayOfWeek.TUESDAY;
  private final int given_할인_가능_순번 = 42;
  private final int given_할인_되지_않는_순번 = given_할인_가능_순번 + 1;

  private final List<DayOfWeek> given_모든_요일 = List.of(
          DayOfWeek.MONDAY,
          DayOfWeek.TUESDAY,
          DayOfWeek.WEDNESDAY,
          DayOfWeek.THURSDAY,
          DayOfWeek.FRIDAY,
          DayOfWeek.SATURDAY,
          DayOfWeek.SUNDAY
  );

  private final List<DayOfWeek> given_할인_되지_않는_요일들 = given_모든_요일.stream()
          .filter(dayOfWeek -> dayOfWeek != given_할인_가능_요일)
          .collect(Collectors.toList());

  abstract class TestDiscountCondition {
    abstract DiscountConditionType givenType();

    DiscountCondition givenDiscountCondition() {
      return new DiscountCondition(
              givenType(),
              given_할인_가능_순번,
              given_할인_가능_요일,
              given_할인_시작_시간,
              given_할인_종료_시간
      );
    }

    boolean subject(DayOfWeek dayOfWeek, LocalTime time) {
      return givenDiscountCondition().isDiscountable(dayOfWeek, time);
    }

    boolean subject(int sequence) {
      return givenDiscountCondition().isDiscountable(sequence);
    }
  }

  @Nested
  @DisplayName("isDiscountable 메소드는")
  class Describe_isDiscountable {
    @Nested
    @DisplayName("DiscountConditionType 이 'PERIOD'일 때")
    class Context_when_period_type extends TestDiscountCondition {
      @Override
      DiscountConditionType givenType() {
        return DiscountConditionType.PERIOD;
      }

      @Nested
      @DisplayName("할인 조건에 맞는 시간이 주어지고, 할인 조건에 맞는 요일이 주어지면")
      class Context_with_valid_time_and_valid_dayOfWeek {
        final List<LocalTime> given_할인_조건에_맞는_시간 = List.of(
                given_할인_시작_시간,
                given_할인_시작_시간.plusSeconds(1),
                given_할인_종료_시간.minusSeconds(1),
                given_할인_종료_시간
        );

        final DayOfWeek given_할인_조건에_맞는_요일 = given_할인_가능_요일;

        @Test
        @DisplayName("true를 리턴한다")
        void it_returns_true() {
          for (LocalTime given_time : given_할인_조건에_맞는_시간) {
            Assertions.assertTrue(subject(given_할인_조건에_맞는_요일, given_time));
          }
        }
      }

      @Nested
      @DisplayName("할인 조건에 맞지 않는 요일이 주어지면")
      class Context_with_invalid_dayOfWeek {
        List<LocalTime> given_시간 = List.of(
                given_할인_시작_시간.minusMinutes(1),
                given_할인_시작_시간,
                given_할인_시작_시간.plusSeconds(1),
                given_할인_종료_시간.minusSeconds(1),
                given_할인_종료_시간,
                given_할인_종료_시간.plusMinutes(1)
        );

        final List<DayOfWeek> given_요일 = given_할인_되지_않는_요일들;

        @Test
        @DisplayName("false를 리턴한다")
        void it_returns_false() {
          for (LocalTime 시간 : given_시간) {
            for (DayOfWeek 할인_조건에_맞지_않는_요일 : given_요일) {
              Assertions.assertFalse(
                      subject(할인_조건에_맞지_않는_요일, 시간),
                      "요일이 맞지 않으면 시간이 어떻건 간에 false를 리턴한다"
              );
            }
          }
        }
      }

      @Nested
      @DisplayName("할인 조건에 맞지 않는 시간이 주어지면")
      class Context_with_invalid_time {
        List<LocalTime> given_시간 = List.of(
                given_할인_시작_시간.minusSeconds(1),
                given_할인_종료_시간.plusSeconds(1)
        );

        @Test
        @DisplayName("false를 리턴한다")
        void it_returns_false() {
          for (LocalTime 할인_되지_않는_시간 : given_시간) {
            for (DayOfWeek 요일 : given_모든_요일) {
              Assertions.assertFalse(subject(요일, 할인_되지_않는_시간), "시간이 맞지 않으면 요일이 어떻건 간에 false를 리턴한다");
            }
          }
        }
      }

      @Nested
      @DisplayName("순번이 주어지면")
      class Context_with_sequence {
        final List<Integer> given_순번 = List.of(
                given_할인_가능_순번,
                given_할인_되지_않는_순번
        );

        @Test
        @DisplayName("예외를 던진다")
        void it_occurs_an_exception() {
          for (int 순번 : given_순번) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> subject(순번));
          }
        }
      }
    }

    @Nested
    @DisplayName("DiscountConditionType이 'SEQUENCE'일 때")
    class Context_with_sequence extends TestDiscountCondition {
      @Override
      DiscountConditionType givenType() {
        return DiscountConditionType.SEQUENCE;
      }

      @Nested
      @DisplayName("할인 조건에 맞는 순번이 주어지면")
      class Context_with_valid_sequence {
        final int given_sequence = given_할인_가능_순번;

        @Test
        @DisplayName("true를 리턴한다")
        void it_returns_true() {
          Assertions.assertTrue(subject(given_sequence));
        }
      }

      @Nested
      @DisplayName("할인 조건에 맞지 않는 순번이 주어지면")
      class Context_with_invalid_sequence {
        final int given_sequence = given_할인_되지_않는_순번;

        @Test
        @DisplayName("false를 리턴한다")
        void it_returns_true() {
          Assertions.assertFalse(subject(given_sequence));
        }
      }

      @Nested
      @DisplayName("요일과 시간이 주어지면")
      class Context_with_DayOfWeek_and_Localtime {
        final List<DayOfWeek> given_요일 = given_모든_요일;
        final List<LocalTime> given_시간 = List.of(
                given_할인_시작_시간.minusMinutes(1),
                given_할인_시작_시간,
                given_할인_시작_시간.plusMinutes(1),
                given_할인_종료_시간.minusMinutes(1),
                given_할인_종료_시간,
                given_할인_종료_시간.plusMinutes(1)
        );

        @Test
        @DisplayName("예외를 던진다")
        void it_occurs_an_exception() {
          for (DayOfWeek 요일 : given_요일) {
            for (LocalTime 시간 : given_시간) {
              Assertions.assertThrows(IllegalArgumentException.class, () -> subject(요일, 시간));
            }
          }
        }
      }
    }
  }
}
