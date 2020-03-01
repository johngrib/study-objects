package com.johngrib.objects._02_movie;

/** 할인 조건. */
public interface DiscountCondition {
  /**
   * 전달된 상영 정보가 할인 조건을 만족시키면 true 를 리턴한다.
   *
   * @param screening 상영 정보
   * @return 할인 조건을 만족했다면 true
   */
  boolean isSatisfiedBy(Screening screening);
}
