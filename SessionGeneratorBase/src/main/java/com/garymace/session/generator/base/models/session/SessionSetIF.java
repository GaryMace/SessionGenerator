package com.garymace.session.generator.base.models.session;

import com.hubspot.immutables.style.HubSpotStyle;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@HubSpotStyle
public interface SessionSetIF {
    int getSetReps();
    int getPostSessionRestSeconds();
    SessionSetType getSessionSetType();
    List<SetItem> getSetItems();
}
