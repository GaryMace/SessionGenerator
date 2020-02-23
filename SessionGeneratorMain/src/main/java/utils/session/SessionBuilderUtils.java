package utils.session;

import com.garymace.session.generator.base.models.session.builder.SessionBuilderAction;
import com.garymace.session.generator.base.models.session.builder.SessionBuilderParams;

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
}
