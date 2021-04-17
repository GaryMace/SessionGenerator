package com.garymace.session.generator.service.shared.utils;

import java.util.function.Predicate;

import com.garymace.session.generator.base.models.session.SessionSet;
import com.garymace.session.generator.base.models.session.builder.SessionBuilderDecision;
import com.garymace.session.generator.base.models.session.builder.SessionBuilderDecisionParams;

public class SessionBuilderDecisionUtils {
  private static final Predicate<SessionBuilderDecisionParams> MAX_DISTANCE_REACHED = sessionBuilderParams ->
    (sessionBuilderParams.getCurrentDistance() * sessionBuilderParams.getCurrentReps()) >=
    sessionBuilderParams.getMaxDistance();
  private static final Predicate<SessionBuilderDecisionParams> IS_TEN_PERCENT_UNDER_MAX_DISTANCE = sessionBuilderParams ->
    (sessionBuilderParams.getCurrentDistance() * sessionBuilderParams.getCurrentReps()) >=
    ((sessionBuilderParams.getMaxDistance() / 100) * 90);
  private static final Predicate<SessionBuilderDecisionParams> MAX_REPS_REACHED = sessionBuilderParams ->
    sessionBuilderParams.getCurrentReps() >= sessionBuilderParams.getMaxReps();

  public SessionBuilderDecisionUtils() {}

  public static SessionBuilderDecision decide(
    SessionBuilderDecisionParams sessionBuilderParams
  ) {
    if (
      MAX_DISTANCE_REACHED.test(sessionBuilderParams) ||
      IS_TEN_PERCENT_UNDER_MAX_DISTANCE.test(sessionBuilderParams)
    ) {
      return SessionBuilderDecision.STOP;
    }
    SessionBuilderDecision action = SessionBuilderDecision.from(
      (int) Math.round(Math.random())
    );

    switch (action) {
      case INCREASE_REPS:
        if (
          MAX_REPS_REACHED.test(sessionBuilderParams) ||
          doesNewRepIncreaseCurrentDistanceTooMuch(sessionBuilderParams)
        ) {
          return SessionBuilderDecision.ADD_NEW_SESSION_SET;
        }
        return action;
      default:
        return action;
    }
  }

  public static boolean isSessionSetTooLarge(
    SessionSet sessionSet,
    int maxDistanceForSessionStage,
    int currentStageReps
  ) {
    // if the set's distance is more than 4 / 5's of the max distance don't add it.
    return (
      (sessionSet.getSetDistance() * currentStageReps) >
      (maxDistanceForSessionStage / 5) *
      4
    );
  }

  private static boolean doesNewRepIncreaseCurrentDistanceTooMuch(
    SessionBuilderDecisionParams sessionBuilderParams
  ) {
    int distanceAfterRepIncrease =
      sessionBuilderParams.getCurrentDistance() *
      (sessionBuilderParams.getCurrentReps() + 1);
    return MAX_DISTANCE_REACHED.test(
      sessionBuilderParams.withCurrentDistance(distanceAfterRepIncrease)
    );
  }
}
