package utils.handler;

import com.garymace.session.generator.base.models.AthleticLevel;
import com.garymace.session.generator.main.service.handler.TrainingSessionGeneratorBaseHandler;
import com.garymace.session.generator.main.service.handler.level.AdvancedTrainingSessionGeneratorHandler;
import com.garymace.session.generator.main.service.handler.level.BeginnerTrainingSessionGeneratorHandler;
import com.garymace.session.generator.main.service.handler.level.IntermediateTrainingSessionGeneratorHandler;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;

import java.util.Map;

public class SessionBriefGeneratorHandlerUtils {

    private Map<AthleticLevel, TrainingSessionGeneratorBaseHandler> athleticLevelToSessionBriefHandlers;

    @Inject
    public SessionBriefGeneratorHandlerUtils(AdvancedTrainingSessionGeneratorHandler advancedSessionBriefGeneratorHandler,
                                             BeginnerTrainingSessionGeneratorHandler beginnerSessionBriefGeneratorHandler,
                                             IntermediateTrainingSessionGeneratorHandler intermediateSessionBriefGeneratorHandler) {
        athleticLevelToSessionBriefHandlers = ImmutableMap.of(
                AthleticLevel.ADVANCED, advancedSessionBriefGeneratorHandler,
                AthleticLevel.BEGINNER, beginnerSessionBriefGeneratorHandler,
                AthleticLevel.INTERMEDIATE, intermediateSessionBriefGeneratorHandler
        );
    }

    public Map<AthleticLevel, TrainingSessionGeneratorBaseHandler> getSessionBriefGeneratorHandlers() {
        return athleticLevelToSessionBriefHandlers;
    }
}
