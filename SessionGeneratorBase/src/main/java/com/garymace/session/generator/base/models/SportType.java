package com.garymace.session.generator.base.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

public enum SportType {
    SWIM("swim"),
    RUN("run"),
    ;

    private static final Map<String, SportType> KEY_TO_SPORT_TYPE = Maps.uniqueIndex(Arrays.asList(values()), SportType::getKey);
    private final String key;

    SportType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @JsonCreator
    public static SportType from(String sportTypeAsString) {
        return KEY_TO_SPORT_TYPE.get(sportTypeAsString);
    }
}
