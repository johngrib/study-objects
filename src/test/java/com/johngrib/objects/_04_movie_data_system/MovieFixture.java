package com.johngrib.objects._04_movie_data_system;

import static com.johngrib.objects._04_movie_data_system.DiscountConditionType.*;
import static java.time.DayOfWeek.*;

import com.johngrib.objects._02_movie.Money;

import java.time.LocalTime;
import java.util.List;

public class MovieFixture {

  final Money given_고정할인금액 = Money.wons(800);
  final double given_할인비율 = 0.1;

  final List<DiscountCondition> 아바타_할인정책 = List.of(
          new DiscountCondition(SEQUENCE, 1),
          new DiscountCondition(SEQUENCE, 10),
          new DiscountCondition(PERIOD, MONDAY, LocalTime.of(10, 0), LocalTime.of(11, 59)),
          new DiscountCondition(PERIOD, THURSDAY, LocalTime.of(10, 0), LocalTime.of(20, 59))
  );

  final Movie AVATAR = Movie.builder()
          .title("아바타")
          .fee(Money.wons(10000))
          .movieType(MovieType.AMOUNT_DISCOUNT)
          .discountAmount(given_고정할인금액)
          .discountConditions(아바타_할인정책)
          .build();

  final List<DiscountCondition> 타이타닉_할인정책 = List.of(
          new DiscountCondition(PERIOD, TUESDAY, LocalTime.of(14, 0), LocalTime.of(16, 59)),
          new DiscountCondition(SEQUENCE, 2),
          new DiscountCondition(PERIOD, THURSDAY, LocalTime.of(10, 0), LocalTime.of(13, 59))
  );

  final Movie TITANIC = Movie.builder()
          .title("타이타닉")
          .fee(Money.wons(11000))
          .discountPercent(given_할인비율)
          .movieType(MovieType.PERCENT_DISCOUNT)
          .discountConditions(타이타닉_할인정책)
          .build();

  final Movie STARWARS = Movie.builder()
          .title("스타워즈")
          .fee(Money.wons(10000))
          .movieType(MovieType.NONE_DISCOUNT)
          .discountConditions(List.of())
          .discountAmount(Money.ZERO)
          .build();
}
