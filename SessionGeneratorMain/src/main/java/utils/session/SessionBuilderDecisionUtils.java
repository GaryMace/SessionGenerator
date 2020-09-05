package utils.session;

import com.garymace.session.generator.base.models.session.SessionSet;
import com.garymace.session.generator.base.models.session.SetItem;
import com.garymace.session.generator.base.models.session.SetType;
import com.garymace.session.generator.base.models.session.builder.SessionBuilderDecision;
import com.garymace.session.generator.base.models.session.builder.SessionBuilderDecisionParams;
import com.garymace.session.generator.base.models.session.rules.SessionRules;
import com.garymace.session.generator.base.models.session.rules.config.DistanceDetail;
import com.google.common.collect.ImmutableList;
import java.util.Random;
import java.util.function.Predicate;
import utils.CollectionUtils;

public class SessionBuilderDecisionUtils {
  private static final Predicate<SessionBuilderDecisionParams> MAX_DISTANCE_REACHED = sessionBuilderParams ->
    sessionBuilderParams.getCurrentDistance() >= sessionBuilderParams.getMaxDistance();
  private static final Predicate<SessionBuilderDecisionParams> MAX_REPS_REACHED = sessionBuilderParams ->
    sessionBuilderParams.getCurrentReps() >= sessionBuilderParams.getMaxReps();

  public SessionBuilderDecisionUtils() {}

  public static SessionBuilderDecision decideOnNextBuilderAction(
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
        if (MAX_REPS_REACHED.test(sessionBuilderParams)) {
          return SessionBuilderDecision.ADD_NEW_SESSION_SET;
        }
        return action;
      default:
        return action;
    }
  }

  public static SessionSet generateSessionSet(SessionRules sessionRules) {
    DistanceDetail distanceDetail = CollectionUtils.getRandomItem(
      sessionRules.getPermittedDistanceDetails()
    );
    SetType setType = CollectionUtils.getRandomItem(
      distanceDetail.getPermittedSetTypes()
    );
    int max = distanceDetail.getRepDetail().getMax();
    int min = distanceDetail.getRepDetail().getMin();

    Random random = new Random();
    // For now just use static rest periods
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
            .setRestSeconds(20)
            .build()
        )
      )
      .build();
  }
}
