package com.garymace.session.generator.main.service.generator.rules;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.base.models.session.rules.SessionRules;
import com.garymace.session.generator.base.models.session.rules.config.Rule;
import com.garymace.session.generator.base.models.session.rules.config.Rules;

import com.google.inject.Inject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class SessionRulesServiceBase {
    private final ObjectMapper objectMapper;

    @Inject
    public SessionRulesServiceBase(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Optional<SessionRules> supplySessionRules(Profile profile, SessionStageType sessionStageType, String filePath) {
        Rules rules = loadJsonAs(filePath, Rules.class);

        Optional<Rule> maybeRule = rules.getRules().stream()
                .filter(athleticLevelRule -> athleticLevelRule.getAthleticLevel() == profile.getAthleticLevel())
                .findFirst();

        if (!maybeRule.isPresent()) {
            throw new RuntimeException(String.format("unable to find rules for athleticLevel: %s", profile.getAthleticLevel()));
        }

        return Optional.of(SessionRules.builder()
                .setMaxReps(maybeRule.get().getMaxReps())
                .setMinDistance(maybeRule.get().getMinDistance())
                .setMaxDistance(maybeRule.get().getMaxDistance())
                .setSessionStageType(sessionStageType)
                .setPermittedDistanceDetails(maybeRule.get().getPermittedDistanceDetails())
                .build());
    }

    private <T> T loadJsonAs(String filename, Class<T> type) {
        return objectMapper.convertValue(loadJson(filename), type);
    }

    private JsonNode loadJson(String filename) {
        String resourceName = "/rules/" + filename;
        try (InputStream fileInputStream = SessionRulesServiceBase.class.getResourceAsStream(resourceName)) {
            if (fileInputStream == null) {
                throw new RuntimeException("Unable to find resource: " + resourceName);
            }
            return objectMapper.readValue(fileInputStream, JsonNode.class);
        } catch (IOException e) {
            throw new RuntimeException("Problem trying to close resource", e);
        }
    }
}
