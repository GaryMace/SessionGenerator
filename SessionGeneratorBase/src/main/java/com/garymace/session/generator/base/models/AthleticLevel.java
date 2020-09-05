package com.garymace.session.generator.base.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.Map;

public enum AthleticLevel {
  BEGINNER("beginner"),
  INTERMEDIATE("intermediate"),
  ADVANCED("advanced");

  private static final Map<String, AthleticLevel> KEY_TO_ATHLETIC_LEVEL = Maps.uniqueIndex(
    Arrays.asList(values()),
    AthleticLevel::getKey
  );
  private final String key;

  AthleticLevel(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  @JsonCreator
  public static AthleticLevel from(String athleticLevelAsString) {
    return KEY_TO_ATHLETIC_LEVEL.get(athleticLevelAsString);
  }
}
