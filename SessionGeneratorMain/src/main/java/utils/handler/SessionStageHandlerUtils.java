package utils.handler;

import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.main.service.handler.SessionStageBaseHandler;
import com.garymace.session.generator.main.service.handler.level.AdvancedTrainingSessionGeneratorHandler;
import com.garymace.session.generator.main.service.handler.level.BeginnerTrainingSessionGeneratorHandler;
import com.garymace.session.generator.main.service.handler.level.IntermediateTrainingSessionGeneratorHandler;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;

import java.util.Map;

public class SessionStageHandlerUtils {
    private Map<SessionStageType, SessionStageBaseHandler> sessionStageToHandler;

    @Inject
    public SessionStageHandlerUtils(AdvancedTrainingSessionGeneratorHandler advancedTrainingSessionGeneratorHandler,
                                    BeginnerTrainingSessionGeneratorHandler beginnerTrainingSessionGeneratorHandler,
                                    IntermediateTrainingSessionGeneratorHandler intermediateTrainingSessionGeneratorHandler) {
        sessionStageToHandler = ImmutableMap.of(
                SessionStageType.WARMUP, advancedTrainingSessionGeneratorHandler,
                SessionStageType.MAINSET, beginnerTrainingSessionGeneratorHandler,
                SessionStageType.COOLDOWN, intermediateTrainingSessionGeneratorHandler
        );
    }

    public Map<SessionStageType, SessionStageBaseHandler> getSessionStageHandlers() {
        return sessionStageToHandler;
    }
}
