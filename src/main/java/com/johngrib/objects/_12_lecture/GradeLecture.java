package com.johngrib.objects._12_lecture;

import java.util.List;

public class GradeLecture extends Lecture {
  private List<Grade> grades;

  public GradeLecture(String title, int pass, List<Grade> grades, List<Integer> scores) {
    super(title, pass, scores);
    this.grades = grades;
  }
}
