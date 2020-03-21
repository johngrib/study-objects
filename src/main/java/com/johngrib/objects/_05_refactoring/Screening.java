package com.johngrib.objects._05_refactoring;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Screening {
  @Getter
  @Setter
  private Movie movie;
  @Getter
  @Setter
  private int sequence;
  @Getter
  @Setter
  private LocalDateTime whenScreened;
}
