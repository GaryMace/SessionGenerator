package utils.handler;

import com.garymace.session.generator.base.models.AthleticLevel;
import com.garymace.session.generator.base.models.session.SessionStage;
import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.main.service.handler.SessionBriefGeneratorBaseHandler;
import com.garymace.session.generator.main.service.handler.SessionStageBaseHandler;
import com.garymace.session.generator.main.service.handler.level.AdvancedSessionBriefGeneratorHandler;
import com.garymace.session.generator.main.service.handler.level.BeginnerSessionBriefGeneratorHandler;
import com.garymace.session.generator.main.service.handler.level.IntermediateSessionBriefGeneratorHandler;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;

import java.util.Map;

public class SessionStageHandlerUtils {

    private Map<SessionStageType, SessionStageBaseHandler> sessionStageToHandler;

    @Inject
    public SessionStageHandlerUtils(AdvancedSessionBriefGeneratorHandler advancedSessionBriefGeneratorHandler,
                                    BeginnerSessionBriefGeneratorHandler beginnerSessionBriefGeneratorHandler,
                                    IntermediateSessionBriefGeneratorHandler intermediateSessionBriefGeneratorHandler) {
        sessionStageToHandler = ImmutableMap.of(
                SessionStageType.WARMUP, advancedSessionBriefGeneratorHandler,
                SessionStageType.MAINSET, beginnerSessionBriefGeneratorHandler,
                SessionStageType.COOLDOWN, intermediateSessionBriefGeneratorHandler
        );
    }

    public Map<SessionStageType, SessionStageBaseHandler> getSessionStageHandlers() {
        return sessionStageToHandler;
    }
}
