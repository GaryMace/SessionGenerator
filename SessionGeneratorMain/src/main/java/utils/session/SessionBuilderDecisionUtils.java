package utils.session;

import java.util.Random;
import java.util.function.Predicate;

import com.garymace.session.generator.base.models.session.SessionSet;
import com.garymace.session.generator.base.models.session.SetItem;
import com.garymace.session.generator.base.models.session.SetType;
import com.garymace.session.generator.base.models.session.builder.SessionBuilderDecision;
import com.garymace.session.generator.base.models.session.builder.SessionBuilderDecisionParams;
import com.garymace.session.generator.base.models.session.rules.SessionRules;
import com.garymace.session.generator.base.models.session.rules.config.DistanceDetail;
import com.garymace.session.generator.base.models.session.rules.config.RepDetail;
import com.google.common.collect.ImmutableList;

import utils.CollectionUtils;

public class SessionBuilderDecisionUtils {
  private static final Predicate<SessionBuilderDecisionParams> MAX_DISTANCE_REACHED = sessionBuilderParams ->
    (sessionBuilderParams.getCurrentDistance() * sessionBuilderParams.getCurrentReps()) >=
    sessionBuilderParams.getMaxDistance();
  private static final Predicate<SessionBuilderDecisionParams> MAX_REPS_REACHED = sessionBuilderParams ->
    sessionBuilderParams.getCurrentReps() >= sessionBuilderParams.getMaxReps();

  public SessionBuilderDecisionUtils() {}

  public static SessionBuilderDecision decide(
    SessionBuilderDecisionParams sessionBuilderParams
  ) {
    if (MAX_DISTANCE_REACHED.test(sessionBuilderParams)) {
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

  public static SessionSet generateSessionSet(SessionRules sessionRules) {
    DistanceDetail distanceDetail = CollectionUtils.getRandomItem(
      sessionRules.getPermittedDistanceDetails()
    );
    SetType setType = CollectionUtils.getRandomItem(
      distanceDetail.getPermittedSetTypes()
    );
    RepDetail repDetailsForSetType = distanceDetail
      .getRepDetails()
      .stream()
      .filter(repDetail -> repDetail.getSetType() == setType)
      .findFirst()
      .get(); // Let's just pretend it's always there.. for now
    int max = repDetailsForSetType.getMax();
    int min = repDetailsForSetType.getMin();

    Random random = new Random();
    int restSeconds = distanceDetail
      .getPostSetRestDurations()
      .stream()
      .filter(repDetail -> repDetail.getSetType() == setType)
      .findFirst()
      .get()
      .getRestDurationSeconds(); // Let's just pretend it's always there.. for now

    return SessionSet
      .builder()
      .setSetReps(random.nextInt((max - min) + 1) + min)
      .setPostSessionRestSeconds(20)
      .setSetType(setType)
      .setSetItems(
        ImmutableList.of(
          SetItem
            .builder()
            .setDistance(distanceDetail.getDistance())
            .setRestSeconds(restSeconds)
            .build()
        )
      )
      .build();
  }
}
