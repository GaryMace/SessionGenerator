package com.garymace.session.generator.main.service.handler.level;

import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.brief.sport.SwimSessionBrief;
import com.garymace.session.generator.main.service.handler.TrainingSessionGeneratorBaseHandler;
import com.google.inject.Inject;

import java.util.Set;

public class BeginnerTrainingSessionGeneratorHandler implements TrainingSessionGeneratorBaseHandler<SwimSessionBrief> {

    @Inject
    public BeginnerTrainingSessionGeneratorHandler() {

    }

    @Override
    public Set<SwimSessionBrief> handle(Profile profile) {
        return null;
    }
}