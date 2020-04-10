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
}