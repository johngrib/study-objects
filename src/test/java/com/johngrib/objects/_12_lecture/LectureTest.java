package com.johngrib.objects._12_lecture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Lecture")
class LectureTest {
  Lecture givenLecture() {
    return new Lecture(
            "객체지향 프로그래밍",
            70,
            Arrays.asList(81, 95, 75, 50, 45)
    );
  }

  @Nested
  @DisplayName("evaluate 메소드")
  class Describe_evaluate {
    @Test
    @DisplayName("이수한 학생의 수와 낙제한 학생의 수를 표현하는 문자열을 리턴한다")
    void it_returns_formatted_string() {
      final String result = givenLecture().evaluate();

      Assertions.assertEquals(result, "Pass:3 Fail:2");
    }
  }
}