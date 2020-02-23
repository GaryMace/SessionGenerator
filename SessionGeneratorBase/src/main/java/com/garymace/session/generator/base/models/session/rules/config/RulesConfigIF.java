package com.garymace.session.generator.base.models.session.rules.config;

import com.hubspot.immutables.style.HubSpotStyle;
import org.immutables.value.Value;

import java.util.Set;

@Value.Immutable
@HubSpotStyle
public interface RulesConfigIF {
    Set<AthleticLevelRules> getAthleticLevelRules();
}
