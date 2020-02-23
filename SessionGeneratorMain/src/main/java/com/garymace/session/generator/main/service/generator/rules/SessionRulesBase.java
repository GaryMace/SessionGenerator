package com.garymace.session.generator.main.service.generator.rules;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.base.models.session.rules.SessionRules;
import com.garymace.session.generator.base.models.session.rules.config.AthleticLevelRules;
import com.garymace.session.generator.base.models.session.rules.config.RulesConfig;
import com.google.inject.Inject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class SessionRulesBase {
    private final ObjectMapper objectMapper;

    @Inject
    public SessionRulesBase(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public SessionRules supplySessionRules(Profile profile, SessionStageType sessionStageType, String filePath) {
        RulesConfig rulesConfig = loadJsonAs(filePath, RulesConfig.class);

        Optional<AthleticLevelRules> maybeAthleticLevelRules = rulesConfig.getAthleticLevelRules().stream()
                .filter(athleticLevelRule -> athleticLevelRule.getAthleticLevel() == profile.getAthleticLevel())
                .findFirst();

        if (!maybeAthleticLevelRules.isPresent()) {
            throw new RuntimeException(String.format("unable to find rules for athleticLevel: %s", profile.getAthleticLevel()));
        }

        return SessionRules.builder()
                .setSessionStageType(sessionStageType)
                .setPermittedDistanceDetails(maybeAthleticLevelRules.get().getDistanceDetails())
                .build();
    }

    private <T> T loadJsonAs(String filename, Class<T> type) {
        return objectMapper.convertValue(loadJson(filename), type);
    }

    private JsonNode loadJson(String filename) {
        String resourceName = "/utils/rules/" + filename;
        try (InputStream fileInputStream = SessionRulesBase.class.getResourceAsStream(resourceName)) {
            if (fileInputStream == null) {
                throw new RuntimeException("Unable to find resource: " + resourceName);
            }
            return objectMapper.readValue(fileInputStream, JsonNode.class);
        } catch (IOException e) {
            throw new RuntimeException("Problem trying to close resource", e);
        }
    }
}
