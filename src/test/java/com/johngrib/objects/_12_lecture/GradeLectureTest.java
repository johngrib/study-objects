package com.johngrib.objects._12_lecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GradeLecture")
class GradeLectureTest {
  Lecture givenLecture() {
    return new GradeLecture(
            "객체지향 프로그래밍",
            70,
            Arrays.asList(
                    new Grade("A", 100, 95),
                    new Grade("B", 94, 80),
                    new Grade("C", 79, 70),
                    new Grade("D", 69, 50),
                    new Grade("F", 49, 0)),
            Arrays.asList(81, 95, 75, 50, 45));
  }

  @Nested
  @DisplayName("evaluate 메소드")
  class Describe_evaluate {
    @Test
    @DisplayName("이수한 학생의 수와 낙제한 학생의 수를 표현하는 문자열을 리턴한다")
    void it_returns_formatted_string() {
      final String result = givenLecture().evaluate();

      Assertions.assertEquals(result, "Pass:3 Fail:2, A:1 B:1 C:1 D:1 F:1");
    }
  }
}