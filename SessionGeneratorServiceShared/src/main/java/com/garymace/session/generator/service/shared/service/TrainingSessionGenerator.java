package com.garymace.session.generator.service.shared.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
import com.garymace.session.generator.service.shared.service.generator.rules.SessionRulesService;
import com.garymace.session.generator.service.shared.utils.SessionBuilderDecisionUtils;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;

import utils.RandomUtils;

public class TrainingSessionGenerator {
  private static final Logger LOG = LoggerFactory.getLogger(
    TrainingSessionGenerator.class
  );

  private final SessionRulesService sessionRulesService;

  @Inject
  public TrainingSessionGenerator(SessionRulesService sessionRulesService) {
    this.sessionRulesService = sessionRulesService;
  }

  public BigDecimal runEvaluation(Profile profile, SessionStageType stageType) {
    BigDecimal overallPercentage = BigDecimal.ZERO;
    int curr = 0;
    int total = 1000;
    while (curr++ < total) {
      overallPercentage = overallPercentage.add(trialSessionForStage(profile, stageType));
    }

    return overallPercentage.divide(BigDecimal.valueOf(total), 4, RoundingMode.HALF_UP);
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
    int maxDistanceForSessionStage = RandomUtils.getInSessionRulesRange(sessionRules);
    int currentDistanceWithoutReps = 0;
    int currentSessionStageReps = 1;
    int totalDistanceWithReps = 0;

    ImmutableSet.Builder<SessionSet> sessionStageSetsBuilder = ImmutableSet.builder();
    while (totalDistanceWithReps < maxDistanceForSessionStage) {
      SessionBuilderDecision sessionBuilderDecision = SessionBuilderDecisionUtils.decide(
        SessionBuilderDecisionParams
          .builder()
          .setCurrentDistance(currentDistanceWithoutReps)
          .setCurrentReps(currentSessionStageReps)
          .setMaxDistance(sessionRules.getMaxDistance())
          .setMaxReps(sessionRules.getMaxReps())
          .build()
      );

      switch (sessionBuilderDecision) {
        case ADD_NEW_SESSION_SET:
          SessionSet sessionSet = SessionSetService.generateSessionSet(sessionRules);
          if (
            SessionBuilderDecisionUtils.isSessionSetTooLarge(
              sessionSet,
              maxDistanceForSessionStage,
              currentSessionStageReps
            )
          ) {
            continue;
          }
          currentDistanceWithoutReps += sessionSet.getSetDistance();
          totalDistanceWithReps = currentDistanceWithoutReps * currentSessionStageReps;
          sessionStageSetsBuilder.add(sessionSet);
          break;
        case INCREASE_REPS:
          currentSessionStageReps++;
          totalDistanceWithReps = currentDistanceWithoutReps * currentSessionStageReps;
          break;
        case STOP:
          return SessionStageDetails
            .builder()
            .setSetCount(currentSessionStageReps)
            .setSessionSets(sessionStageSetsBuilder.build())
            .build();
      }
    }
    logOutStatsForGeneratedDistances(maxDistanceForSessionStage, totalDistanceWithReps);

    return SessionStageDetails
      .builder()
      .setSetCount(currentSessionStageReps)
      .setSessionSets(sessionStageSetsBuilder.build())
      .build();
  }

  private BigDecimal trialSessionForStage(
    Profile profile,
    SessionStageType sessionStageType
  ) {
    SessionRules sessionRules = sessionRulesService.getRules(profile, sessionStageType);
    int maxDistanceForSessionStage = RandomUtils.getInSessionRulesRange(sessionRules);
    int currentDistanceWithoutReps = 0;
    int currentSessionStageReps = 1;
    int totalDistanceWithReps = 0;

    ImmutableSet.Builder<SessionSet> sessionStageSetsBuilder = ImmutableSet.builder();
    while (totalDistanceWithReps < maxDistanceForSessionStage) {
      SessionBuilderDecision sessionBuilderDecision = SessionBuilderDecisionUtils.decide(
        SessionBuilderDecisionParams
          .builder()
          .setCurrentDistance(currentDistanceWithoutReps)
          .setCurrentReps(currentSessionStageReps)
          .setMaxDistance(sessionRules.getMaxDistance())
          .setMaxReps(sessionRules.getMaxReps())
          .build()
      );

      switch (sessionBuilderDecision) {
        case ADD_NEW_SESSION_SET:
          SessionSet sessionSet = SessionSetService.generateSessionSet(sessionRules);
          if (
            SessionBuilderDecisionUtils.isSessionSetTooLarge(
              sessionSet,
              maxDistanceForSessionStage,
              currentSessionStageReps
            )
          ) {
            continue;
          }
          currentDistanceWithoutReps += sessionSet.getSetDistance();
          totalDistanceWithReps = currentDistanceWithoutReps * currentSessionStageReps;
          sessionStageSetsBuilder.add(sessionSet);
          break;
        case INCREASE_REPS:
          currentSessionStageReps++;
          totalDistanceWithReps = currentDistanceWithoutReps * currentSessionStageReps;
          break;
        case STOP:
          return getPercentageAccuracy(maxDistanceForSessionStage, totalDistanceWithReps);
      }
    }
    return getPercentageAccuracy(maxDistanceForSessionStage, totalDistanceWithReps);
  }

  private void logOutStatsForGeneratedDistances(
    int maxDistanceForSessionStage,
    int currentSessionStageDistance
  ) {
    BigDecimal target = BigDecimal.valueOf((float) maxDistanceForSessionStage);
    BigDecimal actual = BigDecimal.valueOf((float) currentSessionStageDistance);
    BigDecimal actualAgainstTarget = actual.divide(target, 4, RoundingMode.HALF_UP);
    actualAgainstTarget = actualAgainstTarget.setScale(4, RoundingMode.HALF_UP);

    BigDecimal rawPercentage = getPercentageAccuracy(
      maxDistanceForSessionStage,
      currentSessionStageDistance
    );
    String percentageDifference;
    if (actualAgainstTarget.floatValue() > 1) {
      percentageDifference = String.format("+%%%s", rawPercentage);
    } else {
      percentageDifference = String.format("-%%%s", rawPercentage);
    }
    LOG.info(
      "Percentage difference between target({}) and actual({}) is {}",
      maxDistanceForSessionStage,
      currentSessionStageDistance,
      percentageDifference
    );
  }

  private static BigDecimal getPercentageAccuracy(
    int maxDistanceForSessionStage,
    int currentSessionStageDistance
  ) {
    BigDecimal target = BigDecimal.valueOf((float) maxDistanceForSessionStage);
    BigDecimal actual = BigDecimal.valueOf((float) currentSessionStageDistance);
    BigDecimal actualAgainstTarget = actual.divide(target, 4, RoundingMode.HALF_UP);
    actualAgainstTarget = actualAgainstTarget.setScale(4, RoundingMode.HALF_UP);

    BigDecimal rawPercentage;
    if (actualAgainstTarget.floatValue() > 1) {
      rawPercentage =
        (actualAgainstTarget.subtract(BigDecimal.ONE)).multiply(BigDecimal.valueOf(100));
    } else {
      rawPercentage =
        BigDecimal
          .valueOf(100)
          .subtract(actualAgainstTarget.multiply(BigDecimal.valueOf(100)));
    }
    return rawPercentage;
  }
}
