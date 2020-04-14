package com.johngrib.objects._12_lecture;

public class Professor {
  private String name;
  private Lecture lecture;

  public Professor(String name, Lecture lecture) {
    this.name = name;
    this.lecture = lecture;
  }

  public String compileStatistics() {
    return String.format("[%s] %s - Avg: %.1f",
            name, lecture.evaluate(), lecture.average());
  }
}
