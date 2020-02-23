package com.garymace.session.generator.base.models.session.rules.config;

import com.hubspot.immutables.style.HubSpotStyle;
import org.immutables.value.Value;

@Value.Immutable
@HubSpotStyle
public interface RepDetailIF {
    int getMin();
    int getMax();
}
