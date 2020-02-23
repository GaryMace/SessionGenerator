package com.garymace.session.generator.main.service.handler.level;

import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.SessionStageDetails;
import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.base.models.session.brief.sport.SwimTrainingSession;
import com.garymace.session.generator.base.models.session.builder.SessionBuilderAction;
import com.garymace.session.generator.base.models.session.builder.SessionBuilderParams;
import com.garymace.session.generator.base.models.session.rules.SessionRules;
import com.garymace.session.generator.main.service.generator.rules.SessionRulesService;
import com.garymace.session.generator.main.service.handler.TrainingSessionGeneratorBaseHandler;

import com.google.inject.Inject;
import utils.session.SessionBuilderUtils;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdvancedTrainingSessionGeneratorHandler implements TrainingSessionGeneratorBaseHandler<SwimTrainingSession> {
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
                        .setWarmupSessionStageDetails(generateWarmupSession(profile))
                        .setMainsetSessionStageDetails(generateWarmupSession(profile))
                        .setCooldownSessionStageDetails(generateWarmupSession(profile))
                        .build()
        ).limit(profile.getWeeklySessionPreference())
        .collect(Collectors.toSet());
    }

    private SessionStageDetails generateWarmupSession(Profile profile) {
        SessionRules warmupSessionRules = sessionRulesService.getRules(profile, SessionStageType.WARMUP);
        int maxDistance = warmupSessionRules.getMaxDistance();

        int currentDistance = 0;
        int currentSets = 0;
        while (currentDistance < maxDistance) {
            SessionBuilderAction sessionBuilderAction = SessionBuilderUtils.decide(SessionBuilderParams.builder()
                    .setCurrentDistance(currentDistance)
                    .setCurrentReps(currentSets)
                    .setMaxDistance(warmupSessionRules.getMaxDistance())
                    .setMaxReps(warmupSessionRules.getMaxReps())
                    .build());

            switch (sessionBuilderAction) {
                case ADD_NEW_SESSION_SET:
                    break;
                case INCREASE_REPS:
                    break;
                case STOP:
                    break;
            }
        }
    }
}