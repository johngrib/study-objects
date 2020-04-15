package com.johngrib.objects._12_lecture;

import java.util.List;

public class FormattedGradeLecture extends GradeLecture {
  public FormattedGradeLecture(String name, int pass, List<Grade> grades, List<Integer> scores) {
    super(name, pass, grades, scores);
  }

  public String formatAverage() {
    return String.format("Avg: %1.1f", super.average());
  }
}
