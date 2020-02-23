package com.garymace.session.generator.main.service.handler.stage.swim;

import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.brief.sport.SwimSessionBrief;
import com.garymace.session.generator.main.service.handler.SessionStageBaseHandler;
import com.google.inject.Inject;

public class WarmupSessionStageHandler implements SessionStageBaseHandler<SwimSessionBrief> {

    @Inject
    public WarmupSessionStageHandler() {

    }

    @Override
    public SwimSessionBrief handle(Profile profile) {
        return null;
    }
}
