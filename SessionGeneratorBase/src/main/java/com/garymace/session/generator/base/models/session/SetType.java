package com.garymace.session.generator.base.models.session;

public enum SetType {
  AEROBIC("Aerobic"),
  ANAEROBIC("Anaerobic"),
  FINS("Fins"),
  FIN_HAND_PADS("Fins & Hand pads"),
  HAND_PADS("Hand pads"),
  INCREMENTAL_THRESHOLD("Increasing effort"),
  PYRAMID("Pyramid"),
  RECOVERY("Recovery"),
  THRESHOLD("Threshold effort"),
  TRIANGLE_SET("Triangle set");

  private final String setTypeLabel;

  SetType(String setTypeLabel) {
    this.setTypeLabel = setTypeLabel;
  }

  public String getSetTypeLabel() {
    return this.setTypeLabel;
  }
}
