package com.garymace.session.generator.base.models.session.rules.config;

import com.garymace.session.generator.base.models.session.SessionStageType;
import com.hubspot.immutables.style.HubSpotStyle;
import org.immutables.value.Value;

import java.util.Set;

@Value.Immutable
@HubSpotStyle
public interface DistanceDetailIF {
    int getDistance();
    Set<SessionStageType> getPermittedSetTypes();
    RepDetail getRepDetail();
}
