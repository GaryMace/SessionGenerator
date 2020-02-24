package utils.session;

import com.garymace.session.generator.base.models.session.SessionSet;
import com.garymace.session.generator.base.models.session.SessionSetType;
import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.base.models.session.SetItem;
import com.garymace.session.generator.base.models.session.builder.SessionBuilderAction;
import com.garymace.session.generator.base.models.session.builder.SessionBuilderParams;
import com.garymace.session.generator.base.models.session.rules.SessionRules;
import com.garymace.session.generator.base.models.session.rules.config.DistanceDetail;
import com.google.common.collect.ImmutableList;
import utils.CollectionUtils;

import java.util.Random;
import java.util.function.Predicate;

public class SessionBuilderUtils {
    private static final Predicate<SessionBuilderParams> MAX_DISTANCE_REACHED = (sessionBuilderParams) ->
            sessionBuilderParams.getCurrentDistance() >= sessionBuilderParams.getMaxDistance();
    private static final Predicate<SessionBuilderParams> MAX_REPS_REACHED = (sessionBuilderParams) ->
            sessionBuilderParams.getCurrentReps() >= sessionBuilderParams.getMaxReps();
    private static final Predicate<SessionBuilderParams> SHOULD_PERFORM_NEW_ACTION = (sessionBuilderParams) ->
            sessionBuilderParams.getCurrentDistance() > 0 && (int)(Math.random() * 100) <= 60;

    public SessionBuilderUtils() {

    }

    public static SessionBuilderAction decide(SessionBuilderParams sessionBuilderParams) {
        if (MAX_DISTANCE_REACHED.test(sessionBuilderParams)
                || !SHOULD_PERFORM_NEW_ACTION.test(sessionBuilderParams)) {
            return SessionBuilderAction.STOP;
        }
        SessionBuilderAction action = SessionBuilderAction.from((int) Math.round(Math.random()));

        switch (action) {
            case INCREASE_REPS:
                if (MAX_REPS_REACHED.test(sessionBuilderParams)) {
                    return SessionBuilderAction.ADD_NEW_SESSION_SET;
                }
                return action;
            default:
                return action;
        }
    }

    public static SessionSet generateSessionSet(SessionRules sessionRules) {
        DistanceDetail distanceDetail = CollectionUtils.getRandomItem(sessionRules.getPermittedDistanceDetails());
        SessionSetType sessionSetType = CollectionUtils.getRandomItem(distanceDetail.getPermittedSetTypes());
        int max = distanceDetail.getRepDetail().getMax();
        int min = distanceDetail.getRepDetail().getMin();

        Random random = new Random();
        // For now just use static rest periods
        return SessionSet.builder()
                .setSetReps(random.nextInt((max - min) + 1) + min)
                .setPostSessionRestSeconds(20)
                .setSessionSetType(sessionSetType)
                .setSetItems(ImmutableList.of(SetItem.builder()
                        .setDistance(distanceDetail.getDistance())
                        .setRestSeconds(20)
                        .build()))
                .build();
    }
}
