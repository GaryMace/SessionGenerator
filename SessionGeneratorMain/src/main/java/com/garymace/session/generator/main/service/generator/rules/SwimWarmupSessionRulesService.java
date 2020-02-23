package com.garymace.session.generator.main.service.generator.rules;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.base.models.session.rules.SessionRules;

import com.google.inject.Inject;


public class SwimWarmupSessionRulesService extends SessionRulesServiceBase {
    public static final String SWIM_WARMUP_RULES_PATH = "swim_warmup_rules.json";

    @Inject
    public SwimWarmupSessionRulesService(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    public SessionRules supplySessionRules(Profile profile) {
        return supplySessionRules(profile, SessionStageType.WARMUP, SWIM_WARMUP_RULES_PATH);
    }
}
