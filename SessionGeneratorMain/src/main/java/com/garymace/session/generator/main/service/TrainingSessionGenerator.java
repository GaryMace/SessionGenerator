package com.garymace.session.generator.main.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.SessionSet;
import com.garymace.session.generator.base.models.session.SessionStageDetails;
import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.base.models.session.brief.sport.SwimTrainingSession;
import com.garymace.session.generator.base.models.session.builder.SessionBuilderDecision;
import com.garymace.session.generator.base.models.session.builder.SessionBuilderDecisionParams;
import com.garymace.session.generator.base.models.session.rules.SessionRules;
import com.garymace.session.generator.main.service.generator.rules.SessionRulesService;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;

import utils.RandomUtils;
import utils.session.SessionBuilderDecisionUtils;

public class TrainingSessionGenerator {
  private static final Logger LOG = LoggerFactory.getLogger(
    TrainingSessionGenerator.class
  );

  private final SessionRulesService sessionRulesService;

  @Inject
  public TrainingSessionGenerator(SessionRulesService sessionRulesService) {
    this.sessionRulesService = sessionRulesService;
  }

  public Set<SwimTrainingSession> generateFrom(Profile profile) {
    return Stream
      .generate(
        () ->
          SwimTrainingSession
            .builder()
            .setWarmupSessionStageDetails(
              generateSessionForStage(profile, SessionStageType.WARMUP)
            )
            .setMainsetSessionStageDetails(
              generateSessionForStage(profile, SessionStageType.MAINSET)
            )
            .setCooldownSessionStageDetails(
              generateSessionForStage(profile, SessionStageType.COOLDOWN)
            )
            .build()
      )
      .limit(profile.getWeeklySessionPreference())
      .collect(Collectors.toSet());
  }

  private SessionStageDetails generateSessionForStage(
    Profile profile,
    SessionStageType sessionStageType
  ) {
    SessionRules sessionRules = sessionRulesService.getRules(profile, sessionStageType);
    int sessionDistance = RandomUtils.getInRange(
      sessionRules.getMinDistance(),
      sessionRules.getMaxDistance()
    );
    int currentDistance = 0;
    int currentSets = 0;

    ImmutableSet.Builder<SessionSet> sessionSets = ImmutableSet.builder();
    while (currentDistance < sessionDistance) {
      LOG.info("Set's are currently: {}", sessionSets);
      SessionBuilderDecision sessionBuilderDecision = SessionBuilderDecisionUtils.decideOnNextBuilderAction(
        SessionBuilderDecisionParams
          .builder()
          .setCurrentDistance(currentDistance)
          .setCurrentReps(currentSets)
          .setMaxDistance(sessionRules.getMaxDistance())
          .setMaxReps(sessionRules.getMaxReps())
          .build()
      );

      switch (sessionBuilderDecision) {
        case ADD_NEW_SESSION_SET:
          SessionSet sessionSet = SessionBuilderDecisionUtils.generateSessionSet(
            sessionRules
          );
          currentDistance += sessionSet.getSetDistance();
          sessionSets.add(sessionSet);
          break;
        case INCREASE_REPS:
          currentSets++;
          currentDistance *= 2;
          break;
        case STOP:
          return SessionStageDetails
            .builder()
            .setSetCount(currentSets)
            .setSessionSets(sessionSets.build())
            .build();
      }
    }
    return SessionStageDetails
      .builder()
      .setSetCount(currentSets)
      .setSessionSets(sessionSets.build())
      .build();
  }
}
