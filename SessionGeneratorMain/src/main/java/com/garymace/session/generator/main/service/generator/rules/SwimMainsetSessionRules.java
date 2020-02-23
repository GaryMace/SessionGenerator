package com.garymace.session.generator.main.service.generator.rules;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.base.models.session.rules.SessionRules;

import com.google.inject.Inject;
import com.google.inject.Singleton;


@Singleton
public class SwimMainsetSessionRules extends SessionRulesBase {
    public static final String SWIM_MAINSET_RULES_PATH = "swim_mainset_rules.json";

    @Inject
    public SwimMainsetSessionRules(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    public SessionRules supplySessionRules(Profile profile) {
        return supplySessionRules(profile, SessionStageType.MAINSET, SWIM_MAINSET_RULES_PATH);
    }
}
