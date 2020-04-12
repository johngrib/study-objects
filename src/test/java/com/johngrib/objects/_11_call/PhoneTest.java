package com.johngrib.objects._11_call;

import com.johngrib.objects._02_movie.Money;
import org.junit.jupiter.api.DisplayName;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Phone")
class PhoneTest {

  // 일반 요금제의 규칙에 따라 통화 요금을 계산하는 전화
  Phone regularPolicyPhone() {
    return new Phone(
            new RegularPolicy(
                    Money.wons(10),
                    Duration.ofSeconds(10)));
  }

  // 심야 할인 요금제의 규칙에 따라 통화 요금을 계산하는 전화
  Phone nightlyDiscountPolicyPhone() {
    return new Phone(
            new NightlyDiscountPolicy(
                    Money.wons(5),
                    Money.wons(10),
                    Duration.ofSeconds(10)));
  }

  // 일반 요금제에 세금 정책을 조합할 경우의 Phone 인스턴스를 생성하는 방법
  Phone taxableRegularPolicyPhone() {
    return new Phone(
            new TaxablePolicy(  // 세금 정책
                    0.05,
                    new RegularPolicy(  // 일반 요금제
                            Money.wons(10),
                            Duration.ofSeconds(10))));
  }

  // 일반 요금제에 기본 요금 할인 정책을 조합한 결과에 세금 정책을 조합하는 방법
  Phone regularRateDiscountTaxablePhone() {
    return new Phone(
            new TaxablePolicy(  // 세금 정책
                    0.05,
                    new RateDiscountablePolicy( // 기본 요금 할인 정책
                            Money.wons(1000),
                            new RegularPolicy(  // 일반 요금제
                                    Money.wons(10),
                                    Duration.ofSeconds(10)))));
  }

  // 순서를 바꾸는 것도 가능하다
  Phone regularRateDiscountTaxablePhone1() {
    return new Phone(
            new RateDiscountablePolicy( // 기본 요금 할인 정책
                    Money.wons(1000),
                    new TaxablePolicy(  // 세금 정책
                            0.05,
                            new RegularPolicy(  // 일반 요금제
                                    Money.wons(10),
                                    Duration.ofSeconds(10)))));
  }

  // 같은 정책을 심야 할인 요금제에도 적용
  Phone rateDiscountNightlyPhone() {
    return new Phone(
            new RateDiscountablePolicy(
                    Money.wons(1000),
                    new TaxablePolicy(
                            0.05,
                            new NightlyDiscountPolicy(
                                    Money.wons(5),
                                    Money.wons(10),
                                    Duration.ofSeconds(10)))));
  }
}