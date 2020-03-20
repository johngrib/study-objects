package com.johngrib.objects._05_movie;

public class SequenceCondition implements DiscountCondition {
  private int sequence;

  public SequenceCondition(int sequence) {
    this.sequence = sequence;
  }

  @Override
  public boolean isSatisfiedBy(Screening screening) {
    return sequence == screening.getSequence();
  }
}
