package com.garymace.session.generator.main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.brief.sport.SwimTrainingSession;
import com.garymace.session.generator.main.config.SessionGeneratorModule;
import com.garymace.session.generator.main.service.TrainingSessionGenerator;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ProfileLoadingUtils;
import utils.TrainingSessionLogger;

public class SessionGeneratorMain {
  private static final Logger LOG = LoggerFactory.getLogger(SessionGeneratorMain.class);
  private static final String VALID_INPUT =
    "{\"athletic_level\":\"...\", \"weekly_session_preference\":\"...\", \"sport_type\":\"...\"}";

  public SessionGeneratorMain() {}

  public static void main(String[] args) throws JsonProcessingException {
    Injector injector = Guice.createInjector(new SessionGeneratorModule());

    ObjectMapper objectMapper = injector.getInstance(ObjectMapper.class);

    LOG.info("Starting programme");
    if (args.length != 1) {
      throw new RuntimeException(
        String.format(
          "Invalid input; expected profileInput file of form: %s",
          VALID_INPUT
        )
      );
    }

    Profile maybeProfile = loadProfile(injector, args);
    Set<SwimTrainingSession> trainingSessions = generateSwimTrainingSession(
      injector,
      maybeProfile
    );
    LOG.info("Session is: {}", objectMapper.writeValueAsString(trainingSessions));

    TrainingSessionLogger.log(trainingSessions);
  }

  private static Profile loadProfile(Injector injector, String[] args) {
    ProfileLoadingUtils profileLoadingUtils = injector.getInstance(
      ProfileLoadingUtils.class
    );

    Optional<Profile> maybeProfile = profileLoadingUtils.loadProfile(args);
    if (!maybeProfile.isPresent()) {
      throw new RuntimeException("An error occurred while attempting to load profile");
    }
    return maybeProfile.get();
  }

  private static Set<SwimTrainingSession> generateSwimTrainingSession(
    Injector injector,
    Profile profile
  ) {
    TrainingSessionGenerator trainingSessionGenerator = injector.getInstance(
      TrainingSessionGenerator.class
    );
    return trainingSessionGenerator.generateFrom(profile);
  }
}
