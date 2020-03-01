package com.johngrib.objects._02_movie;

/** 할인 정책. */
public interface DiscountPolicy {
  /**
   * 할인 금액을 계산한다.
   *
   * @param screening 상영 정보
   * @return 할인 금액
   */
  Money calculateDiscountAmount(Screening screening);
}
