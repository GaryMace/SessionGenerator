package com.garymace.session.generator.base.models.session.builder;

import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.Map;

public enum SessionBuilderDecision {
  ADD_NEW_SESSION_SET(0),
  INCREASE_REPS(1),
  STOP(2);

  private static final Map<Integer, SessionBuilderDecision> ID_TO_ACTIONS = Maps.uniqueIndex(
    Arrays.asList(values()),
    SessionBuilderDecision::getId
  );
  private final int id;

  SessionBuilderDecision(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public static SessionBuilderDecision from(int id) {
    return ID_TO_ACTIONS.get(id);
  }
}
