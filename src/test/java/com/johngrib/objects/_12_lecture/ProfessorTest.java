package com.johngrib.objects._12_lecture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Professor")
class ProfessorTest {

  @Nested
  @DisplayName("compileStatistics 메소드")
  class Describe_compileStatistics {
    @Nested
    @DisplayName("다익스트라 교수의 알고리즘 Lecture 라면")
    class Context_with_dijkstra {
      Professor givenProfessor() {
        return new Professor("다익스트라",
                new Lecture("알고리즘", 70, Arrays.asList(81, 95, 75, 50, 45)));
      }

      @Test
      @DisplayName("이수한 학생의 수, 낙제한 학생의 수, 평균을 표현하는 문자열을 리턴한다")
      void it_returns_statistics() {
        final String result = givenProfessor().compileStatistics();

        assertEquals(result, "[다익스트라] Pass:3 Fail:2 - Avg: 69.2");
      }
    }

    @Nested
    @DisplayName("다익스트라 교수의 알고리즘 GradeLecture 라면")
    class Context_with_dijkstra_grade_lecture {
      Professor givenProfessor() {
        return new Professor("다익스트라",
                new GradeLecture("알고리즘", 70,
                        Arrays.asList(
                                new Grade("A", 100, 95),
                                new Grade("B", 94, 80),
                                new Grade("C", 79, 70),
                                new Grade("D", 69, 50),
                                new Grade("F", 49, 0)),
                        Arrays.asList(81, 95, 75, 50, 45)));
      }

      @Test
      @DisplayName("이수한 학생의 수, 낙제한 학생의 수, 각 학점별 수, 평균을 표현하는 문자열을 리턴한다")
      void it_returns_statistics() {
        final String result = givenProfessor().compileStatistics();

        assertEquals(result, "[다익스트라] Pass:3 Fail:2, A:1 B:1 C:1 D:1 F:1 - Avg: 69.2");
      }
    }
  }

}