package com.johngrib.objects._02_movie;

/**
 * 금액 할인 정책.
 * <p>
 * 조건을 만족할 경우 일정 금액을 할인해주는 정책.
 */
public class AmountDiscountPolicy extends DefaultDiscountPolicy {
  /** 할인 요금. */
  private Money discountAmount;

  public AmountDiscountPolicy(Money discountAmount, DiscountCondition... conditions) {
    super(conditions);
    this.discountAmount = discountAmount;
  }

  @Override
  protected Money getDiscountAmount(Screening screening) {
    return discountAmount;
  }
}
