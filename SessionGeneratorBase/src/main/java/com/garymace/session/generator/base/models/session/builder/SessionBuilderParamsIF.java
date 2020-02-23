package com.garymace.session.generator.base.models.session.builder;

import com.hubspot.immutables.style.HubSpotStyle;
import org.immutables.value.Value;

@Value.Immutable
@HubSpotStyle
public interface SessionBuilderParamsIF {
    int getCurrentDistance();
    int getCurrentReps();
    int getMaxDistance();
    int getMaxReps();
}
