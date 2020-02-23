package com.garymace.session.generator.main.service;

import com.garymace.session.generator.base.models.AthleticLevel;
import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.brief.TrainingSessionBase;
import com.garymace.session.generator.main.service.handler.TrainingSessionGeneratorBaseHandler;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.handler.SessionBriefGeneratorHandlerUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class TrainingSessionGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(TrainingSessionGenerator.class);

    private final Map<AthleticLevel, TrainingSessionGeneratorBaseHandler> athleticLevelToSessionBriefHandlers;

    @Inject
    public TrainingSessionGenerator(SessionBriefGeneratorHandlerUtils sessionBriefGeneratorHandlerUtils) {
        this.athleticLevelToSessionBriefHandlers = sessionBriefGeneratorHandlerUtils.getSessionBriefGeneratorHandlers();
    }

    public <T extends TrainingSessionBase> Set<T> generateFrom(Profile profile) {
        if (!athleticLevelToSessionBriefHandlers.containsKey(profile.getAthleticLevel())) {
            throw new RuntimeException(String.format("Unhandlable athletic type found; expected one of: %s, but got %s",
                    Arrays.toString(AthleticLevel.values()), profile.getAthleticLevel()));
        }
        return athleticLevelToSessionBriefHandlers.get(profile.getAthleticLevel())
                .handle(profile);
    }
}
