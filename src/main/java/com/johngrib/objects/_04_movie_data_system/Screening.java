package com.johngrib.objects._04_movie_data_system;

import com.johngrib.objects._02_movie.Money;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Screening {
  @Getter
  @Setter
  private Movie movie;
  @Getter
  @Setter
  private int sequence;
  @Getter
  @Setter
  private LocalDateTime whenScreened;

  public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
    this.movie = movie;
    this.sequence = sequence;
    this.whenScreened = whenScreened;
  }

  public Money calculateFee(int audienceCount) {
    switch (movie.getMovieType()) {
      case AMOUNT_DISCOUNT:
        if (movie.isDiscountable(whenScreened, sequence)) {
          return movie.calculateAmountDiscountedFee().times(audienceCount);
        }
        break;
      case PERCENT_DISCOUNT:
        if (movie.isDiscountable(whenScreened, sequence)) {
          return movie.calculatePercentDiscountedFee().times(audienceCount);
        }
      case NONE_DISCOUNT:
        return movie.calculateNoneDiscountedFee().times(audienceCount);
    }
    return movie.calculateNoneDiscountedFee().times(audienceCount);
  }
}
