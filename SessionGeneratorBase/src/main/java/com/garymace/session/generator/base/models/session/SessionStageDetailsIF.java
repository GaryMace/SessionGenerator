package com.garymace.session.generator.base.models.session;

import com.hubspot.immutables.style.HubSpotStyle;
import org.immutables.value.Value;

import java.util.Set;

@Value.Immutable
@HubSpotStyle
public interface SessionStageDetailsIF {
    int getSetCount();
    Set<SessionSet> getSessionSets();
}
