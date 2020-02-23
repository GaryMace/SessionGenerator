package com.garymace.session.generator.main.service.generator.rules;

import com.garymace.session.generator.base.models.AthleticLevel;
import com.garymace.session.generator.base.models.profile.Profile;
import com.garymace.session.generator.base.models.session.SessionSetType;
import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.base.models.session.rules.SessionRules;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.Set;

public class SwimWarmupSessionRules {
    private static final Set<SessionSetType> PERMITTED_SET_TYPES = ImmutableSet.of(
            SessionSetType.RECOVERY,
            SessionSetType.AEROBIC,
            SessionSetType.FINS,
            SessionSetType.FIN_HAND_PADS,
            SessionSetType.HAND_PADS
    );
    private static final Map<AthleticLevel, Set<Integer>> LEVEL_TO_PERMITTED_DISTANCES = ImmutableMap.of(
            AthleticLevel.BEGINNER, ImmutableSet.of(25,50,100),
            AthleticLevel.INTERMEDIATE, ImmutableSet.of(25,50,100,150,200),
            AthleticLevel.ADVANCED, ImmutableSet.of(25,50,100,150,200,250,300,350,400)
    );

    public SwimWarmupSessionRules() {

    }

    public static SessionRules supplySessionRules(Profile profile) {
        return SessionRules.builder()
                .setSessionStageType(SessionStageType.WARMUP)
                .setPermittedSetTypes(PERMITTED_SET_TYPES)
                .setPermittedDistances(LEVEL_TO_PERMITTED_DISTANCES.get(profile.getAthleticLevel()))
                .build();
    }
}
