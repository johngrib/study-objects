package com.johngrib.objects._05_movie;

public class SequenceCondition {
  private int sequence;

  public SequenceCondition(int sequence) {
    this.sequence = sequence;
  }

  public boolean isSatisfiedBy(Screening screening) {
    return sequence == screening.getSequence();
  }
}
