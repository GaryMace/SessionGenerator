package com.garymace.session.generator.main.service.handler.level;

import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.SessionSet;
import com.garymace.session.generator.base.models.session.SessionStageDetails;
import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.base.models.session.SetItem;
import com.garymace.session.generator.base.models.session.brief.sport.SwimTrainingSession;
import com.garymace.session.generator.base.models.session.builder.SessionBuilderAction;
import com.garymace.session.generator.base.models.session.builder.SessionBuilderParams;
import com.garymace.session.generator.base.models.session.rules.SessionRules;
import com.garymace.session.generator.main.service.generator.rules.SessionRulesService;
import com.garymace.session.generator.main.service.handler.TrainingSessionGeneratorBaseHandler;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.session.SessionBuilderUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdvancedTrainingSessionGeneratorHandler implements TrainingSessionGeneratorBaseHandler<SwimTrainingSession> {
    private static final Logger LOG = LoggerFactory.getLogger(AdvancedTrainingSessionGeneratorHandler.class);

    private final SessionRulesService sessionRulesService;

    @Inject
    public AdvancedTrainingSessionGeneratorHandler(SessionRulesService sessionRulesService) {
        this.sessionRulesService = sessionRulesService;
    }

    @Override
    public Set<SwimTrainingSession> handle(Profile profile) {
        return generateTrainingSessions(profile);
    }

    private Set<SwimTrainingSession> generateTrainingSessions(Profile profile) {
        return Stream.generate(() ->
                SwimTrainingSession.builder()
                        .setWarmupSessionStageDetails(generateSession(profile, SessionStageType.MAINSET))
                        .setMainsetSessionStageDetails(generateSession(profile, SessionStageType.MAINSET))
                        .setCooldownSessionStageDetails(generateSession(profile, SessionStageType.MAINSET))
                        .build()
        ).limit(profile.getWeeklySessionPreference())
        .collect(Collectors.toSet());
    }

    private SessionStageDetails generateSession(Profile profile, SessionStageType sessionStageType) {
        SessionRules sessionRules = sessionRulesService.getRules(profile, sessionStageType);
        int maxDistance = sessionRules.getMaxDistance();
        int currentDistance = 0;
        int currentSets = 0;

        Set<SessionSet> sessionSets = new HashSet<>();
        while (currentDistance < maxDistance) {
            SessionBuilderAction sessionBuilderAction = SessionBuilderUtils.decide(SessionBuilderParams.builder()
                    .setCurrentDistance(currentDistance)
                    .setCurrentReps(currentSets)
                    .setMaxDistance(sessionRules.getMaxDistance())
                    .setMaxReps(sessionRules.getMaxReps())
                    .build());

            switch (sessionBuilderAction) {
                case ADD_NEW_SESSION_SET:
                    SessionSet sessionSet = SessionBuilderUtils.generateSessionSet(sessionRules);
                    Optional<Integer> maybeDistance = sessionSet.getSetItems().stream()
                            .map(SetItem::getDistance)
                            .reduce(Integer::sum);
                    if (!maybeDistance.isPresent()) {
                        LOG.error("Unexpected error; couldn't calculate distance for SessionSet");
                        continue;
                    }
                    currentDistance += (maybeDistance.get() * sessionSet.getSetReps());
                    sessionSets.add(sessionSet);
                    break;
                case INCREASE_REPS:
                    currentSets++;
                    currentDistance *= 2;
                    break;
                case STOP:
                    break;
            }
        }
        return SessionStageDetails.builder()
                .setSetCount(currentSets)
                .setSessionSets(sessionSets)
                .build();
    }
}