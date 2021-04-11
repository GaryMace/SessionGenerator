package com.garymace.session.generator.service.shared.service.generator.rules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.base.models.session.rules.SessionRules;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class SwimSessionRulesService extends SessionRulesServiceBase {
  private static final Logger LOG = LoggerFactory.getLogger(
    SwimSessionRulesService.class
  );

  public static final String SWIM_COOLDOWN_RULES_PATH = "swim_cooldown_rules.json";
  public static final String SWIM_MAINSET_RULES_PATH = "swim_mainset_rules.json";
  public static final String SWIM_WARMUP_RULES_PATH = "swim_warmup_rules.json";

  @Inject
  public SwimSessionRulesService(ObjectMapper objectMapper) {
    super(objectMapper);
  }

  public Optional<SessionRules> supplySessionRules(
    Profile profile,
    SessionStageType sessionStageType
  ) {
    switch (sessionStageType) {
      case WARMUP:
        return supplySessionRules(profile, sessionStageType, SWIM_WARMUP_RULES_PATH);
      case MAINSET:
        return supplySessionRules(profile, sessionStageType, SWIM_MAINSET_RULES_PATH);
      case COOLDOWN:
        return supplySessionRules(profile, sessionStageType, SWIM_COOLDOWN_RULES_PATH);
      default:
        LOG.warn(
          "Unknown sessionStageType: {}; could not get session rules for: {}",
          sessionStageType,
          profile
        );
        return Optional.empty();
    }
  }
}
