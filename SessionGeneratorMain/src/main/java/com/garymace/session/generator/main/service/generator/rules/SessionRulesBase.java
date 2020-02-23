package com.garymace.session.generator.main.service.generator.rules;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

import java.io.IOException;
import java.io.InputStream;

public class SessionRulesBase {
    private final ObjectMapper objectMapper;

    @Inject
    public SessionRulesBase(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T loadJsonAs(String filename, Class<T> type) {
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
