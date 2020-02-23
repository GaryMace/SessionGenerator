package com.garymace.session.generator.main.service.generator.rules;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.base.models.session.rules.SessionRules;

import com.garymace.session.generator.base.models.session.rules.config.AthleticLevelRules;
import com.garymace.session.generator.base.models.session.rules.config.DistanceDetail;
import com.garymace.session.generator.base.models.session.rules.config.RulesConfig;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Optional;

@Singleton
public class SwimMainsetSessionRules extends SessionRulesBase {
    public static final String SWIM_MAINSET_RULES_PATH = "swim_mainset_rules.json";

    @Inject
    public SwimMainsetSessionRules(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    public SessionRules supplySessionRules(Profile profile) {
        RulesConfig rulesConfig = loadJsonAs(SWIM_MAINSET_RULES_PATH, RulesConfig.class);

        Optional<AthleticLevelRules> maybeAthleticLevelRules = rulesConfig.getAthleticLevelRules().stream()
                .filter(athleticLevelRule -> athleticLevelRule.getAthleticLevel() == profile.getAthleticLevel())
                .findFirst();

        if (!maybeAthleticLevelRules.isPresent()) {
            throw new RuntimeException(String.format("unable to find rules for athleticLevel: %s", profile.getAthleticLevel()));
        }

        return SessionRules.builder()
                .setSessionStageType(SessionStageType.MAINSET)
                .setPermittedDistanceDetails(maybeAthleticLevelRules.get().getDistanceDetails())
                .build();
    }
}
