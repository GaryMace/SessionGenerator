package com.garymace.session.generator.base.models.session.builder;

import com.hubspot.immutables.style.HubSpotStyle;
import org.immutables.value.Value;

@Value.Immutable
@HubSpotStyle
public interface SessionBuilderDecisionParamsIF {
  int getCurrentDistance();
  int getCurrentReps();
  int getMaxDistance();
  int getMaxReps();
}
