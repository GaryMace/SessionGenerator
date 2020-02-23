package com.garymace.session.generator.base.models.session.rules;

import com.garymace.session.generator.base.models.session.SessionStageType;
import com.garymace.session.generator.base.models.session.rules.config.DistanceDetail;
import com.hubspot.immutables.style.HubSpotStyle;
import org.immutables.value.Value;

import java.util.Set;

@Value.Immutable
@HubSpotStyle
public interface SessionRulesIF {
    SessionStageType getSessionStageType();
    Set<DistanceDetail> getPermittedDistanceDetails();
}
