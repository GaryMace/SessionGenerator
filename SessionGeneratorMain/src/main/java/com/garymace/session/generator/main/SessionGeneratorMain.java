package com.garymace.session.generator.main;

import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.base.models.session.brief.sport.SwimTrainingSession;
import com.garymace.session.generator.main.config.SessionGeneratorModule;
import com.garymace.session.generator.main.service.TrainingSessionGenerator;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
  private static final boolean RUN_EVALUATION = false;

  public SessionGeneratorMain() {}

  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new SessionGeneratorModule());

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
    if (RUN_EVALUATION) {
      runEvaluation(injector, maybeProfile);
    }

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

  private static void runEvaluation(Injector injector, Profile profile) {
    TrainingSessionGenerator trainingSessionGenerator = injector.getInstance(
      TrainingSessionGenerator.class
    );
    BigDecimal res = trainingSessionGenerator.runEvaluation(
      profile,
      SessionStageType.MAINSET
    );
    res = res.setScale(4, RoundingMode.HALF_UP);

    LOG.info("Average percentage accuracy: {}", res);
  }
}
