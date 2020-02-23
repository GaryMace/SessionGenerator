package com.garymace.session.generator.main.service.handler.level;


import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.brief.sport.SwimSessionBrief;
import com.garymace.session.generator.main.service.handler.SessionBriefGeneratorBaseHandler;
import com.google.inject.Inject;

import java.util.Set;

public class AdvancedSessionBriefGeneratorHandler implements SessionBriefGeneratorBaseHandler<SwimSessionBrief> {

    @Inject
    public AdvancedSessionBriefGeneratorHandler() {

    }

    @Override
    public Set<SwimSessionBrief> handle(Profile profile) {
        int sessionsPerWeek = profile.getWeeklySessionPreference();

        Set<SwimSessionBrief> sets = generateSessions(profile);
        return null;
    }

    private Set<SwimSessionBrief> generateSessions(Profile profile) {
        SwimSessionBrief warmupSession = generateWarmupSession(profile);
    }

    private SwimSessionBrief generateWarmupSession(Profile profile) {

    }
}