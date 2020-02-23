package com.garymace.session.generator.base.models.session;

import com.hubspot.immutables.style.HubSpotStyle;
import org.immutables.value.Value;

@Value.Immutable
@HubSpotStyle
public interface SetItemIF {
    int getDistance();
    int getRestSeconds();
}
