package com.garymace.session.generator.base.models.session;

import com.hubspot.immutables.style.HubSpotStyle;
import java.util.Set;
import org.immutables.value.Value;

@Value.Immutable
@HubSpotStyle
public interface SessionStageDetailsIF {
  int getSetCount();
  Set<SessionSet> getSessionSets();
}
