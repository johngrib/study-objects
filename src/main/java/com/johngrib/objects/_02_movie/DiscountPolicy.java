package com.johngrib.objects._02_movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 할인 정책. */
public abstract class DiscountPolicy {
  /** 하나의 할인 정책은 여러 개의 할인 조건을 포함할 수 있다. */
  private List<DiscountCondition> conditions = new ArrayList<>();

  public DiscountPolicy(DiscountCondition... conditions) {
    this.conditions = Arrays.asList(conditions);
  }

  /**
   * 할인 금액을 계산한다.
   * <p>
   * Template Method.
   *
   * @param screening
   * @return
   */
  public Money calculateDiscountAmount(Screening screening) {
    for (DiscountCondition each : conditions) {
      if (each.isSatisfiedBy(screening)) {
        return getDiscountAmount(screening);
      }
    }
    return Money.ZERO;
  }

  abstract protected Money getDiscountAmount(Screening screening);
}
