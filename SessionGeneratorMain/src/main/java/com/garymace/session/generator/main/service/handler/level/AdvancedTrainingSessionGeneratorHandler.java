package com.garymace.session.generator.main.service.handler.level;

import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.SessionSet;
import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.base.models.session.brief.sport.SwimSessionBrief;
import com.garymace.session.generator.base.models.session.rules.SessionRules;
import com.garymace.session.generator.main.service.generator.rules.SessionRulesService;
import com.garymace.session.generator.main.service.handler.TrainingSessionGeneratorBaseHandler;

import com.google.inject.Inject;

import java.util.Set;
import java.util.stream.Stream;

public class AdvancedTrainingSessionGeneratorHandler implements TrainingSessionGeneratorBaseHandler<SwimSessionBrief> {

    private final SessionRulesService sessionRulesService;

    @Inject
    public AdvancedTrainingSessionGeneratorHandler(SessionRulesService sessionRulesService) {
        this.sessionRulesService = sessionRulesService;
    }

    @Override
    public Set<SwimSessionBrief> handle(Profile profile) {
        int sessionsPerWeek = profile.getWeeklySessionPreference();

        Set<SwimSessionBrief> sets =  Stream.generate(() -> {
            generateSessions(profile);
        }).limit(profile.getWeeklySessionPreference());

        return null;
    }

    private Set<SwimSessionBrief> generateSessions(Profile profile) {
        SwimSessionBrief warmupSession = generateWarmupSession(profile);
    }

    private SwimSessionBrief generateWarmupSession(Profile profile) {
        SessionRules warmupSessionRules = sessionRulesService.getRules(profile, SessionStageType.WARMUP);

        Set<SessionSet> sets = Stream.generate(() -> {

        }).limit(profile.getWeeklySessionPreference());
    }
}