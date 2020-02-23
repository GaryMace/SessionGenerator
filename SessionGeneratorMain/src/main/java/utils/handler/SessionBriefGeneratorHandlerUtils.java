package utils.handler;

import com.garymace.session.generator.base.models.AthleticLevel;
import com.garymace.session.generator.main.service.handler.SessionBriefGeneratorBaseHandler;
import com.garymace.session.generator.main.service.handler.level.AdvancedSessionBriefGeneratorHandler;
import com.garymace.session.generator.main.service.handler.level.BeginnerSessionBriefGeneratorHandler;
import com.garymace.session.generator.main.service.handler.level.IntermediateSessionBriefGeneratorHandler;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;

import java.util.Map;

public class SessionBriefGeneratorHandlerUtils {

    private Map<AthleticLevel, SessionBriefGeneratorBaseHandler> athleticLevelToSessionBriefHandlers;

    @Inject
    public SessionBriefGeneratorHandlerUtils(AdvancedSessionBriefGeneratorHandler advancedSessionBriefGeneratorHandler,
                                             BeginnerSessionBriefGeneratorHandler beginnerSessionBriefGeneratorHandler,
                                             IntermediateSessionBriefGeneratorHandler intermediateSessionBriefGeneratorHandler) {
        athleticLevelToSessionBriefHandlers = ImmutableMap.of(
                AthleticLevel.ADVANCED, advancedSessionBriefGeneratorHandler,
                AthleticLevel.BEGINNER, beginnerSessionBriefGeneratorHandler,
                AthleticLevel.INTERMEDIATE, intermediateSessionBriefGeneratorHandler
        );
    }

    public Map<AthleticLevel, SessionBriefGeneratorBaseHandler> getSessionBriefGeneratorHandlers() {
        return athleticLevelToSessionBriefHandlers;
    }
}
