package com.garymace.session.generator.service.managers;

import java.util.Set;

import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.brief.sport.SwimTrainingSession;
import com.garymace.session.generator.service.shared.service.TrainingSessionGenerator;
import com.google.inject.Inject;

public class SessionGeneratorManager {

  private final TrainingSessionGenerator trainingSessionGenerator;

  @Inject
  public SessionGeneratorManager(TrainingSessionGenerator trainingSessionGenerator) {
    this.trainingSessionGenerator = trainingSessionGenerator;
  }

  public Set<SwimTrainingSession> generateSession(Profile userProfile) {

  }
}
