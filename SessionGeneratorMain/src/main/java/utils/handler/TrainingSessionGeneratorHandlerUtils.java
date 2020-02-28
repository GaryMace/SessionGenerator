package utils.handler;

import com.garymace.session.generator.base.models.AthleticLevel;
import com.garymace.session.generator.main.service.handler.TrainingSessionGeneratorBaseHandler;
import com.garymace.session.generator.main.service.handler.level.AdvancedTrainingSessionGeneratorHandler;
import com.garymace.session.generator.main.service.handler.level.BeginnerTrainingSessionGeneratorHandler;
import com.garymace.session.generator.main.service.handler.level.IntermediateTrainingSessionGeneratorHandler;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;

import java.util.Map;

public class TrainingSessionGeneratorHandlerUtils {

    private Map<AthleticLevel, TrainingSessionGeneratorBaseHandler> athleticLevelToTrainingSessionGeneratorHandlers;

    @Inject
    public TrainingSessionGeneratorHandlerUtils(AdvancedTrainingSessionGeneratorHandler advancedTrainingSessionGeneratorHandler,
                                                BeginnerTrainingSessionGeneratorHandler beginnerTrainingSessionGeneratorHandler,
                                                IntermediateTrainingSessionGeneratorHandler intermediateTrainingSessionGeneratorHandler) {
        athleticLevelToTrainingSessionGeneratorHandlers = ImmutableMap.of(
                AthleticLevel.ADVANCED, advancedTrainingSessionGeneratorHandler,
                AthleticLevel.BEGINNER, beginnerTrainingSessionGeneratorHandler,
                AthleticLevel.INTERMEDIATE, intermediateTrainingSessionGeneratorHandler
        );
    }

    public Map<AthleticLevel, TrainingSessionGeneratorBaseHandler> getSessionBriefGeneratorHandlers() {
        return athleticLevelToTrainingSessionGeneratorHandlers;
    }
}
