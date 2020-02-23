package com.garymace.session.generator.main.service;

import com.garymace.session.generator.base.models.AthleticLevel;
import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.brief.SessionBriefBase;
import com.garymace.session.generator.base.models.session.brief.sport.SwimSessionBrief;
import com.garymace.session.generator.main.service.handler.SessionBriefGeneratorBaseHandler;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.handler.SessionBriefGeneratorHandlerUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class SessionBriefGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(SessionBriefGenerator.class);

    private final Map<AthleticLevel, SessionBriefGeneratorBaseHandler> athleticLevelToSessionBriefHandlers;

    @Inject
    public SessionBriefGenerator(SessionBriefGeneratorHandlerUtils sessionBriefGeneratorHandlerUtils) {
        this.athleticLevelToSessionBriefHandlers = sessionBriefGeneratorHandlerUtils.getSessionBriefGeneratorHandlers();
    }

    public <T extends SessionBriefBase> Set<T> generateFrom(Profile profile) {
        if (!athleticLevelToSessionBriefHandlers.containsKey(profile.getAthleticLevel())) {
            throw new RuntimeException(String.format("Unhandlable athletic type found; expected one of: %s, but got %s",
                    Arrays.toString(AthleticLevel.values()), profile.getAthleticLevel()));
        }
        return athleticLevelToSessionBriefHandlers.get(profile.getAthleticLevel())
                .handle(profile);
    }
}
