package com.garymace.session.generator.base.models.session.rules.config;

import java.util.Set;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.garymace.session.generator.base.models.session.SetType;
import com.hubspot.immutables.style.HubSpotStyle;

@Value.Immutable
@HubSpotStyle
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public interface DistanceDetailIF {
  int getDistance();
  Set<SetType> getPermittedSetTypes();
  Set<PostSetRestDuration> getPostSetRestDurations();
  Set<RepDetail> getRepDetails();
}
