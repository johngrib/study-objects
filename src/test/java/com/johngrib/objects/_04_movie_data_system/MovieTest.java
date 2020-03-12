package com.johngrib.objects._04_movie_data_system;

import com.johngrib.objects._02_movie.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SuppressWarnings({"InnerClassMayBeStatic", "NonAsciiCharacters"})
@DisplayName("Movie 클래스")
class MovieTest extends MovieFixture {
  @Nested
  @DisplayName("calculateAmountDiscountedFee 메소드는")
  class Describe_calculateAmountDiscountedFee {
    @Nested
    @DisplayName("movieType이 AMOUNT_DISCOUNT인 영화라면")
    class Context_with_amount_discount {
      final Movie givenMovie = AVATAR;

      @Test
      @DisplayName("할인된 금액을 표현하는 Money 객체를 리턴한다")
      void it_returns_discounted_money() {
        final Money discounted = givenMovie.calculateAmountDiscountedFee();

        Assertions.assertTrue(discounted.isLessThan(givenMovie.getFee()),
                "리턴된 금액은 할인된 금액이다");
        Assertions.assertEquals(givenMovie.getFee().minus(discounted), givenMovie.getDiscountAmount(),
                "영화에 등록된 할인 금액만큼 할인된다");
      }
    }

    @Nested
    @DisplayName("movieType이 AMOUNT_DISCOUNT 가 아닌 영화라면")
    class Context_with_not_amount_discount {
      final List<Movie> givenMovies = List.of(TITANIC, STARWARS);

      @Test
      @DisplayName("예외를 던진다")
      void it_returns_discounted_money() {
        for (Movie givenMovie : givenMovies) {
          Assertions.assertThrows(IllegalArgumentException.class, givenMovie::calculateAmountDiscountedFee);
        }
      }
    }
  }

  @Nested
  @DisplayName("calculatePercentDiscountedFee 메소드는")
  class Describe_calculatePercentDiscountedFee {
    @Nested
    @DisplayName("movieType이 PERCENT_DISCOUNT인 영화라면")
    class Context_with_percent_discount {
      final Movie givenMovie = TITANIC;

      @Test
      @DisplayName("할인된 금액을 표현하는 Money 객체를 리턴한다")
      void it_returns_discounted_money() {
        final Money discounted = givenMovie.calculatePercentDiscountedFee();

        Assertions.assertTrue(discounted.isLessThan(givenMovie.getFee()),
                "리턴된 금액은 할인된 금액이다");
        Assertions.assertEquals(givenMovie.getFee().times(1 - givenMovie.getDiscountPercent()), discounted,
                "영화에 등록된 할인 비율만큼 할인된다");
      }
    }

    @Nested
    @DisplayName("movieType이 PERCENT_DISCOUNT 가 아닌 영화라면")
    class Context_with_not_percent_discount {
      final List<Movie> givenMovies = List.of(AVATAR, STARWARS);

      @Test
      @DisplayName("예외를 던진다")
      void it_returns_discounted_money() {
        for (Movie givenMovie : givenMovies) {
          Assertions.assertThrows(IllegalArgumentException.class, givenMovie::calculatePercentDiscountedFee);
        }
      }
    }
  }

  @Nested
  @DisplayName("calculateNoneDiscountedFee 메소드는")
  class Describe_calculateNoneDiscountedFee {
    @Nested
    @DisplayName("movieType이 NONE_DISCOUNT인 영화라면")
    class Context_with_none_discount {
      final Movie givenMovie = STARWARS;

      @Test
      @DisplayName("할인된 금액을 표현하는 Money 객체를 리턴한다")
      void it_returns_discounted_money() {
        final Money discounted = givenMovie.calculateNoneDiscountedFee();

        Assertions.assertEquals(givenMovie.getFee(), discounted, "NONE_DISCOUNT는 실제로는 할인을 하지 않는다");
      }
    }

    @Nested
    @DisplayName("movieType이 NONE_DISCOUNT 가 아닌 영화라면")
    class Context_with_not_percent_discount {
      final List<Movie> givenMovies = List.of(AVATAR, TITANIC);

      @Test
      @DisplayName("예외를 던진다")
      void it_returns_discounted_money() {
        for (Movie givenMovie : givenMovies) {
          Assertions.assertThrows(IllegalArgumentException.class, givenMovie::calculateNoneDiscountedFee);
        }
      }
    }
  }

  @Nested
  @DisplayName("isDiscountable 메소드")
  class Describe_isDiscountable {
    final Movie givenMovie = AVATAR;

    final LocalDateTime time = LocalDateTime.of(2020, 3, 2, 0, 0, 0); // 월요일

    @Nested
    @DisplayName("할인 가능한 순번이 주어지면")
    class Context_with_valid_sequence {
      final int givenSequence = 1;

      // 1시 30분, 2시 30분, ... , 23시 30분
      final List<LocalDateTime> givenTimes = IntStream.range(1, 23)
              .mapToObj(num -> time.withHour(num).withMinute(30))
              .collect(Collectors.toList());

      @Test
      @DisplayName("true를 리턴한다")
      void it_returns_true() {
        for (LocalDateTime givenTime : givenTimes) {
          Assertions.assertTrue(givenMovie.isDiscountable(givenTime, givenSequence), "시간과는 관계 없이 true를 리턴한다");
        }
      }
    }

    @Nested
    @DisplayName("할인 가능한 시간이 주어지면")
    class Context_with_valid_time {
      final int[] givenSequences = IntStream.range(-100, 100).toArray();
      final LocalDateTime givenTime = time.withHour(10).withMinute(30);

      @Test
      @DisplayName("true를 리턴한다")
      void it_returns_true() {
        for (int givenSequence : givenSequences) {
          Assertions.assertTrue(givenMovie.isDiscountable(givenTime, givenSequence), "순번과는 관계 없이 true를 리턴한다");
        }
      }
    }

    @Nested
    @DisplayName("할인되지 않는 순번과 시간이 주어지면")
    class Context_with_invalid_sequence_and_invalid_time {
      final int givenSequence = -1;
      final LocalDateTime givenTime = time.withHour(1).withMinute(30);

      @Test
      @DisplayName("false를 리턴한다")
      void it_returns_false() {
        Assertions.assertFalse(givenMovie.isDiscountable(givenTime, givenSequence));
      }
    }
  }
}