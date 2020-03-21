package com.johngrib.objects._05_refactoring;

import com.johngrib.objects._02_movie.Money;

public class ReservationAgency {
  public Reservation reserve(Screening screening, Customer customer, int audienceCount) {
    boolean discountable = checkDiscountable(screening);
    Money fee = calculateFee(screening, discountable, audienceCount);
    return createReservation(screening, customer, audienceCount, fee);
  }

  private boolean checkDiscountable(Screening screening) {
    return screening.getMovie().getDiscountConditions().stream()
            .anyMatch(condition -> isDiscountable(condition, screening));
  }

  private boolean isDiscountable(DiscountCondition condition, Screening screening) {
    if (condition.getType() == DiscountConditionType.PERIOD) {
      return isSatisfiedByPeriod(condition, screening);
    }
    return isSatisfiedBySequence(condition, screening);
  }

  private boolean isSatisfiedByPeriod(DiscountCondition condition, Screening screening) {
    return screening.getWhenScreened().getDayOfWeek().equals(condition.getDayOfWeek())
            && condition.getStartTime().isBefore(screening.getWhenScreened().toLocalTime())
            && condition.getEndTime().isAfter(screening.getWhenScreened().toLocalTime());
  }

  private boolean isSatisfiedBySequence(DiscountCondition condition, Screening screening) {
    return condition.getSequence() == screening.getSequence();
  }

  private Money calculateFee(Screening screening, boolean discountable, int audienceCount) {
    if (discountable) {
      return screening.getMovie().getFee()
              .minus(calculateDiscountedFee(screening.getMovie()))
              .times(audienceCount);
    }
    return screening.getMovie().getFee();
  }

  private Money calculateDiscountedFee(Movie movie) {
    switch (movie.getMovieType()) {
      case AMOUNT_DISCOUNT:
        return calculateAmountDiscountedFee(movie);
      case PERCENT_DISCOUNT:
        return calculatePercentDiscountedFee(movie);
      case NONE_DISCOUNT:
        return calculateNoneDiscountedFee(movie);
    }
    throw new IllegalArgumentException();
  }

  private Money calculateAmountDiscountedFee(Movie movie) {
    return movie.getDiscountAmount();
  }

  private Money calculatePercentDiscountedFee(Movie movie) {
    return movie.getFee().times(movie.getDiscountPercent());
  }

  private Money calculateNoneDiscountedFee(Movie movie) {
    return movie.getFee();
  }

  private Reservation createReservation(Screening screening, Customer customer, int audienceCount, Money fee) {
    return new Reservation(customer, screening, fee, audienceCount);
  }
}
