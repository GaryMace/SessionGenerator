package com.garymace.session.generator.base.models.session.builder;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;

public enum SessionBuilderAction {
    ADD_NEW_SESSION_SET(0),
    INCREASE_REPS(1),
    STOP(2),
    ;
    private static final Map<Integer, SessionBuilderAction> ID_TO_ACTIONS = Maps.uniqueIndex(Arrays.asList(values()), SessionBuilderAction::getId);
    private final int id;

    SessionBuilderAction(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static SessionBuilderAction from(int id) {
        return ID_TO_ACTIONS.get(id);
    }
}
